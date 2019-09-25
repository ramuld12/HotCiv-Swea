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
  public void redVictoyCountShouldIncrementWhenWinning() {
    Position redArcher = new Position(2, 0);
    Position blueLegion = new Position(3,2);
    game.moveUnit(redArcher, new Position(3,1));
    endOfRound();
    game.moveUnit(new Position(3,1), blueLegion);
    assertThat(game.getVictoriesForPlayer(Player.RED), is(1));
  }

  @Test
  public void blueVictoyCountShouldIncrementWhenWinning() {
    game.endOfTurn(); // So blue is in turn
    Position redArcher = new Position(2, 0);
    Position blueLegion = new Position(3,2);
    game.moveUnit(blueLegion, new Position(3,1));
    endOfRound();
    game.moveUnit(new Position(3,1), redArcher);
    assertThat(game.getVictoriesForPlayer(Player.BLUE), is(1));
  }

  @Test
  public void redShouldWinWhenVictoryCountReach3(){
    HashMap<Position, UnitImpl> units = game.getUnits();
    units.put(new Position(3,1), new UnitImpl(GameConstants.SETTLER,Player.BLUE));
    units.put(new Position(2,1), new UnitImpl(GameConstants.SETTLER,Player.BLUE));
    game.moveUnit(new Position(2,0), new Position(2,1));
    endOfRound();
    game.moveUnit(new Position(2,1),new Position(3,1));
    endOfRound();
    game.moveUnit(new Position(3,1), new Position(3,2));
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void blueShouldWinWhenVictoryCountReach3(){
    game.endOfTurn();
    HashMap<Position, UnitImpl> units = game.getUnits();
    units.put(new Position(3,1), new UnitImpl(GameConstants.SETTLER,Player.RED));
    units.put(new Position(2,1), new UnitImpl(GameConstants.SETTLER,Player.RED));
    game.moveUnit(new Position(3,2), new Position(3,1));
    endOfRound();
    game.moveUnit(new Position(3,1),new Position(2,1));
    endOfRound();
    game.moveUnit(new Position(2,1), new Position(2,0));

    assertThat(game.getWinner(), is(Player.BLUE));
  }
}

