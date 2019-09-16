package hotciv.standard.strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public interface WinningStrategy {
  public Player getWinner(GameImpl game);
}
