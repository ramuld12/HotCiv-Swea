package hotciv.standard;

import hotciv.standard.CityImpl;

import hotciv.framework.*;
import hotciv.utility.Utility;

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
  private HashMap<Position, Tile> map; //HashMap for representing the different tiletypes
  private HashMap<Position, City> cities; //HashMap representing the cities
  private HashMap<Position, UnitImpl> units; //HashMap representing the units

  /**Constructor method for gameImp
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
    cities.put(new Position(4,1),new CityImpl(Player.BLUE));

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
    String toType = map.get(to).getTypeString();
    if (toType.equals(GameConstants.OCEANS) || toType.equals(GameConstants.MOUNTAINS)) {return false;}
    if (units.get(from) == null) {return false;} //Making sure there is a unit at from position
    if (units.get(from).getOwner() != p) {return false;} //Making sure the player in turn can only move his/her own units
    if (units.get(to) != null) {units.remove(to);} //Attacking unit should always win
    if (units.get(from).getMoveCount() < 1) {return false;}
    for (Position po : Utility.get8neighborhoodOf(from)) {
      if (po.equals(to)) {
        String unitType = units.get(from).getTypeString();
        units.put(to, new UnitImpl(unitType, p));
        units.remove(from);
        ((UnitImpl)units.get(to)).decreaseMoveCount();
        return true;
      }
    }
    return false;
  }
  public void endOfTurn() {//Not fully implemented
    if (p.equals(Player.RED)) {
      p = Player.BLUE;
    } else {
      p = Player.RED;
      gameAge += 100;
      ((CityImpl)cities.get(new Position(1,1))).incrementTreas();
      units.values().forEach(UnitImpl::resetMoveCounter);
    }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}//Not implemented stadig ikke
  public void changeProductionInCityAt( Position p, String unitType ) {}//Not implemented
  public void performUnitActionAt( Position p ) {}//Not implemented stadig ikke
}
