package hotciv.standard;

import hotciv.framework.*;
import hotciv.utility.*;

import java.util.HashMap;

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
  private Player p = Player.RED; //The current player in turn, initially set to red
  private int gameAge = -4000; //The current age of the game, initially set to -4000
  private HashMap<Position, TileImpl> map; //HashMap for representing the different tiletypes
  private HashMap<Position, CityImpl> cities; //HashMap representing the cities
  private HashMap<Position, UnitImpl> units; //HashMap representing the units

  /**Constructor method for GameImpl
   * Initializes the private variables with tiletypes and city initial positions for the cities.
   */
  public GameImpl() {
    //Initialize the gameboard
    map = new HashMap<>();
    for (int i = 0; i < GameConstants.WORLDSIZE; i++){
      for (int j = 0; j < GameConstants.WORLDSIZE; j++){
        map.put(new Position(i,j), new TileImpl(GameConstants.PLAINS));
      }
    }
    map.put(new Position(1,0), new TileImpl(GameConstants.OCEANS));
    map.put(new Position(0,1), new TileImpl(GameConstants.HILLS));
    map.put(new Position(2,2), new TileImpl(GameConstants.MOUNTAINS));

    //Initialize the citites map
    cities = new HashMap<>();
    cities.put(new Position(1,1), new CityImpl(Player.RED));
    cities.put(new Position(4,1), new CityImpl(Player.BLUE));

    //Initialize the units map
    units = new HashMap<>();
    units.put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED));
    units.put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    units.put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED));
  }

  public Tile getTileAt( Position p ) { return map.get(p);}
  public Unit getUnitAt( Position p ) { return units.get(p); }
  public City getCityAt( Position p ) { return cities.get(p); }
  public Player getPlayerInTurn() { return p;}
  public Player getWinner() {
    if (this.gameAge >= -3000) {return Player.RED;}
    return null;
  }
  public int getAge() { return gameAge; }
  public boolean moveUnit( Position from, Position to ) {
    if (!map.containsKey(from) || !map.containsKey(to)) {return false;} //Only allow units to move within the boundries of the map
    if (!map.get(to).isValidMovementTileType()) {return false;} //Units can not moce to certain tiles
    if (units.get(from) == null) {return false;} //Making sure there is a unit at from position
    if (units.get(from).getOwner() != p) {return false;} //Making sure the player in turn can only move his/her own units
    if (units.get(to) != null) {units.remove(to);} //Attacking unit should always win
    if (units.get(from).getMoveCount() < 1) {return false;} //Units need to have a positive move counter to move
    for (Position po : Utility.get8neighborhoodOf(from)) {
      if (po.equals(to)) {
        //Find the tile the unit is trying to move to,
        // create a new unit with moveCounter 0 of the same type there and remove the old
        String unitType = units.get(from).getTypeString();
        units.put(to, new UnitImpl(unitType, p));
        units.remove(from);
        units.get(to).decreaseMoveCount();
        return true;
      }
    }
    return false;
  }
  public void endOfTurn() {
    if (p.equals(Player.RED)) {
      p = Player.BLUE;
    } else {
      p = Player.RED;
      gameAge += 100;
      cities.get(new Position(1,1)).increaseTreas();
      cities.get(new Position(4,1)).increaseTreas();
      units.values().forEach(UnitImpl::resetMoveCounter);
      cities.keySet().forEach(p -> produceUnitInCityAt(p, cities.get(p)));
    }
  }

  private void produceUnitInCityAt(Position p, CityImpl c) {
    if (c.hasEnoughTreasure()) {
      c.reduceTreasury(c.getProdCost());
      if (units.get(p) == null) {
        units.put(p, new UnitImpl(c.getProduction(), c.getOwner()));
      }
    }
      else {
        for (Position po : Utility.get8neighborhoodOf(p)) {
          //find the next vacant spot around the city
          if (units.get(po) == null && map.get(po).isValidMovementTileType()) {
            units.put(po, new UnitImpl(c.getProduction(), c.getOwner()));
            break;
          }
        }
      }
  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}//Not implemented
  public void changeProductionInCityAt( Position p, String unitType ) {
    cities.get(p).changeProduction(unitType);
  }
  public void performUnitActionAt( Position p ) {}//Not implemented

}
