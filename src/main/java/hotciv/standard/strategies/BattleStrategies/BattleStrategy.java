package hotciv.standard.strategies.BattleStrategies;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface BattleStrategy {

  /**
   * Method for handling a battle between two opposing units.
   * @param game the current game
   * @param attackingPosition Position of the attacing unit
   * @param defendingPosition Position of the defending unit
   * @return true if attacking unit won the battle
   */
  public boolean handlingOfAttack(GameImpl game, Position attackingPosition, Position defendingPosition);
}
