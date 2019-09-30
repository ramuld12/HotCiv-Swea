package hotciv.standard.strategies.WorldLayoutStrategies;

import hotciv.framework.*;
import hotciv.standard.*;

import java.util.HashMap;

public class DeltaCivWorldLayoutStrategy implements WorldLayoutStrategy {
  @Override
  public void createTheWorld(GameImpl game) {
    // Basically we use a 'data driven' approach - code the
    // layout in a simple semi-visual representation, and
    // convert it to the actual Game representation.
    String[] layout =
            new String[] {
                    "...ooMooooo.....",
                    "..ohhoooofffoo..",
                    ".oooooMooo...oo.",
                    ".ooMMMoooo..oooo",
                    "...ofooohhoooo..",
                    ".ofoofooooohhoo.",
                    "...ooo..........",
                    ".ooooo.ooohooM..",
                    ".ooooo.oohooof..",
                    "offfoooo.offoooo",
                    "oooooooo...ooooo",
                    ".ooMMMoooo......",
                    "..ooooooffoooo..",
                    "....ooooooooo...",
                    "..ooohhoo.......",
                    ".....ooooooooo..",
            };
    // Conversion...
    HashMap<Position, TileImpl> world = game.getWorld();
    String line;
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      line = layout[r];
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        char tileChar = line.charAt(c);
        String type = "error";
        if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
        if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
        if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
        if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
        if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
        Position p = new Position(r,c);
        world.put( p, new TileImpl(type));
      }
    }

    //Initialize the citites map
    HashMap<Position, CityImpl> cities = game.getCities();
    cities.put(new Position(8, 12), new CityImpl(Player.RED));
    cities.put(new Position(4, 5), new CityImpl(Player.BLUE));

    //No specifications are given for the unit map, in FRS ex. 36.3.3,
    //so we have not initialized it

  }
}
