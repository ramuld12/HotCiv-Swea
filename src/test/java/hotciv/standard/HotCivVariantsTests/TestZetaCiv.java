package hotciv.standard.HotCivVariantsTests;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.ZetaCivFactory;
import hotciv.standard.strategies.WinningStrategies.BetaCivWinningStrategy;
import hotciv.standard.strategies.WinningStrategies.EpsilonCivWinningStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class TestZetaCiv {
  private GameImpl game;

  /**
   * Fixture for alphaciv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(new ZetaCivFactory());
    assertThat(game, is(notNullValue()));
  }

  /**
   * Method for testing end of round triggers
   */
  private void endOfRound() {
    game.endOfTurn();
    game.endOfTurn();
  }

  @Test
  public void numberOfRoundsShouldIncrementEachRound() {
    endOfRound();
    endOfRound();
    assertThat(game.getRoundNumber(), is(2));
    endOfRound();
    endOfRound();
    endOfRound();
    assertThat(game.getRoundNumber(), is(5));
  }

  @Test
  public void shouldBeBetaCivWinningStrategyBefore20() {
    game.setRoundNumber(10);
    assertEquals(game.getWinningStrategy().getCurrentState().getClass(), BetaCivWinningStrategy.class);
  }

  @Test
  public void shouldBeEpsilonCivWinningStrategyAfter20() {
    game.setRoundNumber(25);
    endOfRound();
    assertEquals(game.getWinningStrategy().getCurrentState().getClass(), EpsilonCivWinningStrategy.class);
  }

  @Test
  public void shouldChangeWinningStrategyAt20Rounds() {
    game.setRoundNumber(19);
    assertEquals(game.getWinningStrategy().getCurrentState().getClass(), BetaCivWinningStrategy.class);
    endOfRound();
    assertEquals(game.getWinningStrategy().getCurrentState().getClass(), EpsilonCivWinningStrategy.class);
  }

  @Test
  public void shouldResetnumberOfVictoriesWhenRound20Starts(){

  }
}
