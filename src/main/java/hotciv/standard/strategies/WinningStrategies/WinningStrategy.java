package hotciv.standard.strategies.WinningStrategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import java.util.HashMap;

public interface WinningStrategy {

  /**
   * Returns the winner of the game
   * according to the given winningStrategy
   * @param game the current game
   * @return the Player who won
   */
  public Player getWinner(GameImpl game);

  public void initializePlayerVictories(GameImpl game);
}
