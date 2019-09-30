package hotciv.standard.strategies.UnitActionStrategies;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface UnitActionStrategy {

  /**
   * Initiates the action of a given unit according
   * to the implementing strategy
   * @param game the current game
   * @param p position of the unit for action
   */
  public void performUnitActionAt(GameImpl game, Position p);

}
