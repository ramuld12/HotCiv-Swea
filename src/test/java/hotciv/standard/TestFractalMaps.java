package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.HotCivFactory.AlphaCivFactory;
import hotciv.standard.TestStubs.ThirdPartyWorldLayoutFactory;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
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
    ArrayList<TileImpl> tiles = new ArrayList<>();
    for (int i = 0; i<25; i++) {
      Game game = new GameImpl(new ThirdPartyWorldLayoutFactory());
      tiles.add((TileImpl)game.getTileAt(new Position(1,1)));
    }

    boolean testSameType = true;
    for(TileImpl tile : tiles) {
      if(!tile.equals(tiles.get(0))) {
        testSameType = false;
      }
    }
    assertThat(testSameType, is(false));
  }
}
