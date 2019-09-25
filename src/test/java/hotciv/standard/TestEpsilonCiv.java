package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.strategies.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for EpsilonCiv test cases
 *
 */


public class TestEpsilonCiv {
  private GameImpl game;

  /**
   * Fixture for alphaciv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(new AlphaCivAgingStrategy(), new EpsilonCivWinningStrategy(), new AlphaCivUnitActionStrategy(), new AlphaCivWorldLayoutStrategy(), new EpsilonCivBattleStrategy());
    assertThat(game, is(notNullValue()));
  }

  /**
   * Method for testing end of round triggers
   */
  public void endOfRound() {
    game.endOfTurn();
    game.endOfTurn();
  }

  @Test
  public void redAndBlueShouldStartWith0Victories() {
    assertThat(game.getVictoriesForPlayer(Player.RED), is(0));
    assertThat(game.getVictoriesForPlayer(Player.BLUE), is(0));
  }


  @Test
  public void attackCountShouldIncrementWhenWinning() {
    Position redArcher = new Position(2, 0);
    Position blueLegion = new Position(3,2);
    game.moveUnit(redArcher, new Position(3,1));
    endOfRound();
    game.moveUnit(new Position(3,1), blueLegion);
    assertThat(game.getVictoriesForPlayer(Player.RED), is(1));
  }

}

