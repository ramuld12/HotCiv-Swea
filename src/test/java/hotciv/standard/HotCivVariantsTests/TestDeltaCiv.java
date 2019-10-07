package hotciv.standard.HotCivVariantsTests;

import hotciv.framework.*;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.DeltaCivFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

// Skeleton class for Deltaciv test cases

public class TestDeltaCiv {
  private Game game;

  /** Fixture for Deltaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new DeltaCivFactory());
    assertThat(game, is(notNullValue()));
  }

  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void redCityShouldBeAt8_12() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(8,12);
    assertThat(game.getCityAt(p).getOwner(), is(Player.RED));
  }

  @Test
  public void blueCityShouldBeAt4_5() {
    Position p = new Position(4,5);
    assertThat(game.getCityAt(p).getOwner(), is(Player.BLUE));
  }

  @Test
  public void shouldBeOceanAtTile1_0() {
    Position p = new Position(0,1);
    assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.OCEANS));
  }

  @Test
  public void shouldBeHillsAtTile1_3() {
    Position p = new Position(1,3);
    assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.HILLS));
  }

  @Test
  public void shouldBeMountainsAtTile5_0() {
    Position p = new Position(0,5);
    assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.MOUNTAINS));
  }

  @Test
  public void shouldBePlainsByDefault() {
    assertThat(game.getTileAt(new Position(3,15)).getTypeString(), is(GameConstants.PLAINS));
    assertThat(game.getTileAt(new Position(10,4)).getTypeString(), is(GameConstants.PLAINS));
  }


}



