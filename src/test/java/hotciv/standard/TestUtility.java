package hotciv.standard;

import hotciv.framework.Position;
import hotciv.utility.Utility;

import java.util.HashMap;
import java.util.PriorityQueue;

public class TestUtility {

  private GameImpl game;
  private HashMap<Position, UnitImpl> units;

  public TestUtility(GameImpl game) {
    this.game = game;
    units = game.getUnits();
  }

  /**
   * Method for testing end of round triggers
   */
  public void endOfRound() {
    game.endOfTurn();
    game.endOfTurn();
  }

  public void removeNeighbours(Position p){
    for (Position neighbourPosition : Utility.get8neighborhoodOf(p)) {
      boolean isUnitPresent = game.getUnitAt(neighbourPosition) != null;
      if(isUnitPresent){
        units.remove(neighbourPosition);
      }
    }
  }

}
