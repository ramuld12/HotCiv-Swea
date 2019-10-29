package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.HotCivFactory.ThirdPartyWorldLayoutFactory;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class TestFractalMaps {


  @Test
  public void shouldHaveTileAt1_1() {
    Game game = new GameImpl(new ThirdPartyWorldLayoutFactory());
    assertNotNull(game.getTileAt(new Position(1,1)));
  }

  @Test
  public void shouldHaveDifferentTilesAt1_1For25Games() {
    ArrayList<String> tileTypes = new ArrayList<>();
    for (int i = 0; i<25; i++) {
      Game game = new GameImpl(new ThirdPartyWorldLayoutFactory());
      tileTypes.add(game.getTileAt(new Position(1,1)).getTypeString());
    }

    boolean testSameType = false;
    for(String tileType : tileTypes) {
      if(!tileType.equals(tileTypes.get(0))) {
        testSameType = true;
        break;
      }
    }
    assertThat(testSameType, is(true));
  }
}
