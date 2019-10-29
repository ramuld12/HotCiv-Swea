package hotciv.standard.adapters;

import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.strategies.WorldLayoutStrategies.WorldLayoutStrategy;
import hotciv.utility.Utility;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;

public class WorldGeneratorAdapter implements WorldLayoutStrategy {
  private ThirdPartyFractalGenerator generator;
  private Utility util = new Utility();

  public WorldGeneratorAdapter() {
    generator = new ThirdPartyFractalGenerator();
  }

  @Override
  public void createTheWorld(GameImpl game) {
    HashMap<Position, TileImpl> world = game.getWorld();
    for ( int r = 0; r < 16; r++ ) {
      for ( int c = 0; c < 16; c++ ) {
        char tileChar = generator.getLandscapeAt(r,c);
        String type = util.getTileTypeFromChar(tileChar);
        Position p = new Position(r,c);
        world.put( p, new TileImpl(type));
      }
    }
  }
}
