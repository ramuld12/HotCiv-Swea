package hotciv.standard.strategies.WinningStrategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class AlphaCivWinningStrategy implements WinningStrategy {
  @Override
  public Player getWinner(GameImpl game) {
    if (game.getAge() >= -3000) {return Player.RED;}
    return null;
  }

  @Override
  public void initializePlayerVictories(GameImpl game) {

  }

  @Override
  public WinningStrategy getCurrentState() {
    return this;
  }
}
