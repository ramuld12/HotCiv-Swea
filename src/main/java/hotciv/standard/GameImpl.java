package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.strategies.AgingStrategy;
import hotciv.standard.strategies.UnitActionStrategy;
import hotciv.standard.strategies.WinningStrategy;
import hotciv.utility.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {
  private final WinningStrategy winningStrategy;
  private final AgingStrategy agingStrategy;
  private final UnitActionStrategy unitActionStrategy;
  private Player playerInTurn = Player.RED; //The current player in turn, initially set to red
  private int gameAge = -4000; //The current age of the game, initially set to -4000
  private HashMap<Position, TileImpl> world; //HashMap for representing the different tiletypes
  private HashMap<Position, CityImpl> cities; //HashMap representing the cities
  private HashMap<Position, UnitImpl> units; //HashMap representing the units

  /**
   * Constructor method for GameImpl
   * Initializes the private variables with tiletypes and city initial positions for the cities.
   */
  public GameImpl(AgingStrategy a, WinningStrategy w, UnitActionStrategy ua) {
    this.agingStrategy = a;
    this.winningStrategy = w;
    this.unitActionStrategy = ua;

    //Initialize the gameboard
    world = new HashMap<>();
    for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
      for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
        world.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
      }
    }
    world.put(new Position(1, 0), new TileImpl(GameConstants.OCEANS));
    world.put(new Position(0, 1), new TileImpl(GameConstants.HILLS));
    world.put(new Position(2, 2), new TileImpl(GameConstants.MOUNTAINS));

    //Initialize the citites map
    cities = new HashMap<>();
    cities.put(new Position(1, 1), new CityImpl(Player.RED));
    cities.put(new Position(4, 1), new CityImpl(Player.BLUE));

    //Initialize the units map
    units = new HashMap<>();
    units.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
    units.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    units.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED));
  }

  public Tile getTileAt(Position p) {
    return world.get(p);
  }

  public Unit getUnitAt(Position p) {
    return units.get(p);
  }

  public City getCityAt(Position p) {
    return cities.get(p);
  }

  public Player getPlayerInTurn() {
    return playerInTurn;
  }

  public Player getWinner() {
    return winningStrategy.getWinner(this);
  }

  public int getAge() {
    return gameAge;
  }

  public boolean isOpponentCityEmpty(Position from, Position to) {
    boolean isTheCityVacant = units.get(to) == null;
    boolean isTheCityForeign = cities.get(to).getOwner() != playerInTurn;
    if (isTheCityVacant && isTheCityForeign) {
      return true;
    }
    return false;
  }

  public boolean moveUnit(Position from, Position to) {
    boolean isThereACityAtPositionTo = cities.get(to) != null;

    if (!world.containsKey(from) || !world.containsKey(to)) {
      return false;
    } //Only allow units to move within the boundries of the map
    if (!world.get(to).isValidMovementTileType()) {
      return false;
    } //Units can not move to certain tiles
    if (units.get(from) == null) {
      return false;
    } //Making sure there is a unit at from position
    if (units.get(from).getOwner() != playerInTurn) {
      return false;
    } //Making sure the player in turn can only move his/her own units
    if (units.get(to) != null &&
            units.get(from).getOwner() == units.get(to).getOwner()) {
      return false;
    } //Units should not move to tile with other units from the same owner
    if (units.get(to) != null) {
      units.remove(to);
    } //Attacking unit should always win
    if (units.get(from).getMoveCount() < 1) {
      return false;
    } //Units need to have a positive move counter to move

    if (isThereACityAtPositionTo) {
      if (isOpponentCityEmpty(from, to)) {
        cities.get(to).changeOwner(playerInTurn);
      }
    }

    for (Position po : Utility.get8neighborhoodOf(from)) {
      if (po.equals(to)) {
        //Find the tile the unit is trying to move to,
        // create a new unit with moveCounter 0 of the same type there and remove the old
        String unitType = units.get(from).getTypeString();
        units.put(to, new UnitImpl(unitType, playerInTurn));
        units.remove(from);
        units.get(to).decreaseMoveCount();
        return true;
      }
    }
    return false;
  }

  public void endOfTurn() {
    if (playerInTurn.equals(Player.RED)) {
      playerInTurn = Player.BLUE;
    } else {
      playerInTurn = Player.RED;
      gameAge += agingStrategy.getAgeStep(this);
      cities.get(new Position(1, 1)).increaseTreas();
      cities.get(new Position(4, 1)).increaseTreas();
      units.values().forEach(UnitImpl::resetMoveCounter);
      cities.keySet().forEach(p -> produceUnitInCityAt(p, cities.get(p)));
    }
  }

  private void produceUnitInCityAt(Position p, CityImpl c) {
    boolean isThereAUnitAtPosition = units.get(p) != null;
    if (c.hasEnoughTreasure()) {
      c.reduceTreasury(c.getProdCost());
      if (!isThereAUnitAtPosition) {
        units.put(p, new UnitImpl(c.getProduction(), c.getOwner()));
      }
    } else {
      for (Position neighbourPosition : Utility.get8neighborhoodOf(p)) {
        boolean isThereAUnitAtNeighbourPosition = units.get(neighbourPosition) == null;
        boolean isValidTileInWorld = world.get(neighbourPosition).isValidMovementTileType();
        if (isThereAUnitAtNeighbourPosition && isValidTileInWorld) {
          units.put(neighbourPosition, new UnitImpl(c.getProduction(), c.getOwner()));
          break;
        }
      }
    }
  }


  public void setGameAge(int newGameAge) {
    gameAge = newGameAge;
  }

  public void changeWorkForceFocusInCityAt(Position p, String balance) {
  }//Not implemented

  public void changeProductionInCityAt(Position p, String unitType) {
    cities.get(p).changeProduction(unitType);
  }

  public void performUnitActionAt(Position p) {
    unitActionStrategy.performUnitActionAt(this, p);
  }

  public void removeUnitFromUnitsMapAtPosition(Position unitPosition) {
    boolean isThereAUnitAtPosition = units.get(unitPosition) != null;
    if (isThereAUnitAtPosition) {
      units.remove(unitPosition);
    }
  }

  public boolean doesPlayerInTurnOwnAllCities() {
    Set<Player> owners = new HashSet<>();
    cities.values().forEach(city -> owners.add(city.getOwner()));
    if (owners.size() == 1) {
      return true;
    }
    return false;
  }

  public void createCityAtPosition(Position position) {
    boolean isPositionVacantForCity = cities.get(position) == null;
    if (isPositionVacantForCity) {
      cities.put(position, new CityImpl(playerInTurn));
    }
  }


}
