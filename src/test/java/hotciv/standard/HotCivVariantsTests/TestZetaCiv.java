package hotciv.standard.HotCivVariantsTests;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.ZetaCivFactory;
import hotciv.standard.UnitImpl;
import hotciv.standard.strategies.WinningStrategies.BetaCivWinningStrategy;
import hotciv.standard.strategies.WinningStrategies.EpsilonCivWinningStrategy;
import hotciv.standard.strategies.WinningStrategies.ZetaCivWinningStrategy;
import hotciv.utility.Utility;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

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
  public void redShouldwinWhenConqueringAllCititsBefore20Rounds() {
    Position p1 = new Position(4,3);
    Position p2 = new Position(4,2);
    Position p3 = new Position(4,1);

    game.moveUnit(p1,p2);
    endOfRound();
    game.moveUnit(p2,p3);
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void numberOfRoundsShouldIncrementEachRound() {
    endOfRound();
    endOfRound();
    assertThat(game.getRoundNumber(), is(2));
  }

  @Test
  public void shouldBeBetaCivWinningStrategyBefore20() {
    game.setRoundNumber(10);
    assertEquals(game.getWinningStrategy().getCurrentState().getClass(), BetaCivWinningStrategy.class);
  }

  @Test
  public void shouldBeEpsilonCivWinningStrategyAfter20() {
    game.setRoundNumber(25);
    game.getWinner();
    assertEquals(game.getWinningStrategy().getCurrentState().getClass(), EpsilonCivWinningStrategy.class);
  }

}
