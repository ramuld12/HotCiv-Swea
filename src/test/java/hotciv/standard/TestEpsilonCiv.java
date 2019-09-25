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
    game = new GameImpl(new AlphaCivAgingStrategy(), new EpsilonCivWinningStrategy(), new AlphaCivUnitActionStrategy(), new AlphaCivWorldLayoutStrategy());
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
  public void redShouldStartWith0Victories() {
    assertThat(game.getVictories(Player.RED), is(0));
  }

  /*public void attackCountShouldIncrementWhenWinning() {


  }*/

}

