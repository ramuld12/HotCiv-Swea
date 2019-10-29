package hotciv.standard.adapters;

import hotciv.standard.GameImpl;
import hotciv.standard.strategies.WorldLayoutStrategies.WorldLayoutStrategy;
import thirdparty.ThirdPartyFractalGenerator;

public class WorldGeneratorAdapter implements WorldLayoutStrategy {

  @Override
  public void createTheWorld(GameImpl game) {
    ThirdPartyFractalGenerator generator = new ThirdPartyFractalGenerator();
    String line;
    for ( int r = 0; r < 16; r++ ) {
      line = "";
      for ( int c = 0; c < 16; c++ ) {
        line = line + generator.getLandscapeAt(r,c);
      }
      System.out.println( line );
    }
  }
}
