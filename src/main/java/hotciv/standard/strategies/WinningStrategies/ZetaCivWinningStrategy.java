package hotciv.standard.strategies.WinningStrategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import java.util.HashMap;

public class ZetaCivWinningStrategy implements WinningStrategy {
  private WinningStrategy
    before20RoundsStrategy, after20RoundsStrategy, currentState;

  public ZetaCivWinningStrategy(WinningStrategy before20, WinningStrategy after20) {
    this.before20RoundsStrategy = before20;
    this.after20RoundsStrategy = after20;
    currentState = before20; //Should start with this as default
  }

  @Override
  public Player getWinner(GameImpl game) {
    return currentState.getWinner(game);
  }

  @Override
  public void changeStateIfNeeded(GameImpl game) {
    boolean isBefore20Rounds = game.getRoundNumber() < 20;
    if (isBefore20Rounds) { currentState = before20RoundsStrategy; }
    else {
      currentState = after20RoundsStrategy;
      after20RoundsStrategy.initializePlayerVictories(game);
    }
  }

  @Override
  public void initializePlayerVictories(GameImpl game) {
  }

  public WinningStrategy getCurrentState() {return currentState;}
}
