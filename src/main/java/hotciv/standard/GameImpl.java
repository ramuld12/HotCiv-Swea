package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.strategies.AgingStrategy;
import hotciv.standard.strategies.UnitActionStrategy;
import hotciv.standard.strategies.WinningStrategy;
import hotciv.standard.strategies.WorldLayoutStrategy;
import hotciv.utility.*;

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
  private final WorldLayoutStrategy worldLayoutStrategy;
  private Player playerInTurn = Player.RED; //The current player in turn, initially set to red
  private int gameAge = -4000; //The current age of the game, initially set to -4000
  private HashMap<Position, TileImpl> world; //HashMap for representing the different tiletypes
  private HashMap<Position, CityImpl> cities; //HashMap representing the cities
  private HashMap<Position, UnitImpl> units; //HashMap representing the units

  /**
   * Constructor method for GameImpl, which can create a GameImpl for Alpha-, Beta-, Delta-
   * and GammaCiv depending on strategies given.
   * Initializes the private variables with tiletypes, city initial positions for the cities
   * and initial unit placements in the world.
   */
  public GameImpl(AgingStrategy a, WinningStrategy w, UnitActionStrategy ua, WorldLayoutStrategy ws) {
    this.agingStrategy = a;
    this.winningStrategy = w;
    this.unitActionStrategy = ua;
    this.worldLayoutStrategy = ws;
    world = new HashMap<>();
    cities = new HashMap<>();
    units = new HashMap<>();
    worldLayoutStrategy.createTheWorld(this);
  }

  //Simple accessor and setter methods
  public HashMap<Position, TileImpl> getWorld() {
    return world;
  }

  public HashMap<Position, CityImpl> getCities() {
    return cities;
  }

  public HashMap<Position, UnitImpl> getUnits() {
    return units;
  }

  public TileImpl getTileAt(Position p) {
    return world.get(p);
  }

  public UnitImpl getUnitAt(Position p) {
    return units.get(p);
  }

  public CityImpl getCityAt(Position p) {
    return cities.get(p);
  }

  public Player getPlayerInTurn() {
    return playerInTurn;
  }

  public int getAge() {
    return gameAge;
  }

  public void setGameAge(int newGameAge) {
    gameAge = newGameAge;
  }

  public Player getWinner() {
    return winningStrategy.getWinner(this);
  }

  public void createCityAtPosition(Position position) {
    boolean isPositionVacantForCity = cities.get(position) == null;
    if (isPositionVacantForCity) {
      cities.put(position, new CityImpl(playerInTurn));
    }
  }


  public boolean moveUnit(Position from, Position to) {
    boolean isFromInTheWorld = world.containsKey(from);
    boolean isToInTheWorld = world.containsKey(to);
    boolean isTileTypeAtToValidForMovement = world.get(to).isValidMovementTileType();
    boolean isThereAUnitAtFrom = units.get(from) != null;
    boolean isThereAUnitAtTo = units.get(to) != null;
    boolean isUnitOwnedByPlayerInTurn = isThereAUnitAtFrom && units.get(from).getOwner() == playerInTurn;
    boolean isThereAlreadyAFriendlyUnitAtTo = isThereAUnitAtFrom && isThereAUnitAtTo && units.get(from).getOwner() == units.get(to).getOwner();
    boolean isThereAnArcherAtPositionFrom = isThereAUnitAtFrom && units.get(from).getTypeString().equals(GameConstants.ARCHER);
    boolean isArcherFortified = isThereAUnitAtFrom && units.get(from).getDefensiveStrength() == 6;
    boolean hasMovesLeft = isThereAUnitAtFrom && units.get(from).getMoveCount() > 0;

    if ( ! (isFromInTheWorld &&
            isToInTheWorld &&
            isTileTypeAtToValidForMovement &&
            isThereAUnitAtFrom &&
            isUnitOwnedByPlayerInTurn &&
            !isThereAlreadyAFriendlyUnitAtTo &&
            !(isThereAnArcherAtPositionFrom && isArcherFortified) &&
            hasMovesLeft))
      return false;

    //Handling of attack on a city
    boolean isThereACityAtPositionTo = cities.containsKey(to);
    boolean isTheCityForeign = isThereACityAtPositionTo && cities.get(to).getOwner() != playerInTurn;

    if (    isThereACityAtPositionTo &&
            isTheCityForeign)
        cities.get(to).changeOwner(playerInTurn);

    //Handling of movement for the unit
    for (Position unitPosition : Utility.get8neighborhoodOf(from)) {
      if (unitPosition.equals(to)) {
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
      cities.values().forEach(CityImpl::increaseTreas);
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
    }
    else {
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




  public void changeWorkForceFocusInCityAt(Position p, String balance) {
  }//Not implemented

  public void changeProductionInCityAt(Position p, String unitType) {
    cities.get(p).changeProduction(unitType);
  }

  public void performUnitActionAt(Position p) {
    unitActionStrategy.performUnitActionAt(this, p);
  }


  /**
   * Removes a unit at a certain position from the units map,
   * if a unit is present at that position
   * @param unitPosition the position of the unit to be removed
   */
  public void removeUnitFromUnitsMapAtPosition(Position unitPosition) {
    boolean isThereAUnitAtPosition = units.get(unitPosition) != null;
    if (isThereAUnitAtPosition) { units.remove(unitPosition); }
  }



  // === Simple Boolean methods ======================================

  /**
   *  Creates a set, and check if it has a size of one,
   *  indicating one player owns all cities
   * @return true if player in turn owns all the cities
   */
  public boolean doesPlayerInTurnOwnAllCities() {



    Set<Player> owners = new HashSet<>();
    cities.values().forEach(city -> owners.add(city.getOwner()));
    boolean doesAPlayerOwnAllCitites = owners.size() == 1;
    return doesAPlayerOwnAllCitites && owners.contains(playerInTurn);
  }




}
