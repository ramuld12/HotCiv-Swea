package hotciv.standard.strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public interface WinningStrategy {

  /**
   * Returns the winner of the game
   * according to the given winningStrategy
   * @param game the current game
   * @return the Player who won
   */
  public Player getWinner(GameImpl game);
}
