package hotciv.standard;

import hotciv.standard.CityImpl;

import hotciv.framework.*;

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
  private int gameAge = 4000; //The current age of the game, initially set to 4000
  private HashMap<Position, Tile> map; //HashMap for representing tiletypes
  private HashMap<Position, City> cities;


  public GameImpl() {
    map = new HashMap<>();
    for (int i = 0; i < GameConstants.WORLDSIZE; i++){
      for (int j = 0; j < GameConstants.WORLDSIZE; j++){
        map.put(new Position(i,j), new TileImpl(GameConstants.PLAINS));
      }
    }
    map.put(new Position(1,0), new TileImpl(GameConstants.OCEANS));
    map.put(new Position(0,1), new TileImpl(GameConstants.HILLS));
    map.put(new Position(2,2), new TileImpl(GameConstants.MOUNTAINS));

    cities = new HashMap<>();
    cities.put(new Position(1,1), new CityImpl(Player.RED));
    cities.put(new Position(4,1),new CityImpl(Player.BLUE));

  }

  public Tile getTileAt( Position p ) { return map.get(p);}
  public Unit getUnitAt( Position p ) { return null; }//Not implemented
  public City getCityAt( Position p ) { return cities.get(p); }
  public Player getPlayerInTurn() { return p;}
  public Player getWinner() {
    return Player.RED;
  }//(fake it)
  public int getAge() { return gameAge; }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }//Not implemented
  public void endOfTurn() {//Not fully implemented
    if (p.equals(Player.RED)) {
      p = Player.BLUE;
    } else {
      p = Player.RED;
      gameAge -= 100;
      ((CityImpl)cities.get(new Position(1,1))).incrementTreas();
    }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}//Not implemented
  public void changeProductionInCityAt( Position p, String unitType ) {}//Not implemented
  public void performUnitActionAt( Position p ) {}//Not implemented
}
