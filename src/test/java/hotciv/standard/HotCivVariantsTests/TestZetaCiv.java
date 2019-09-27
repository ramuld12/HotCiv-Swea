package hotciv.standard.HotCivVariantsTests;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.ZetaCivFactory;
import hotciv.standard.UnitImpl;
import hotciv.standard.strategies.*;
import hotciv.utility.Utility;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestZetaCiv {
  private GameImpl game;
  private HashMap<Position, UnitImpl> units;

  /**
   * Fixture for alphaciv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(new ZetaCivFactory());
    assertThat(game, is(notNullValue()));
    units = game.getUnits();
  }

  /**
   * Method for testing end of round triggers
   */
  private void endOfRound() {
    game.endOfTurn();
    game.endOfTurn();
  }

  private void removeNeighbours(Position p){
    for (Position neighbourPosition : Utility.get8neighborhoodOf(p)) {
      boolean isUnitPresent = game.getUnitAt(neighbourPosition) != null;
      if(isUnitPresent){
        units.remove(neighbourPosition);
      }
    }
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
  public void redShouldWinAfter3SuccessfulAttacksAfter20Rounds() {

  }

}
