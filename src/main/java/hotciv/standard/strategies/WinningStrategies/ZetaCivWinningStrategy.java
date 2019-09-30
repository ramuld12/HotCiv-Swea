package hotciv.standard.strategies.WinningStrategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class ZetaCivWinningStrategy implements WinningStrategy {
  private WinningStrategy ws = new BetaCivWinningStrategy();
  private WinningStrategy
    before20RoundsStrategy, after20RoundsStrategy, currentState;

  public ZetaCivWinningStrategy(WinningStrategy before20, WinningStrategy after20) {
    this.before20RoundsStrategy = before20;
    this.after20RoundsStrategy = after20;
    currentState = null;
  }


  @Override
  public Player getWinner(GameImpl game) {
    boolean isBefore20Rounds = game.getRoundNumber() < 20;
    if (isBefore20Rounds) {
      currentState = before20RoundsStrategy;
    }
    else {
      currentState = after20RoundsStrategy;
    }
    return currentState.getWinner(game);
  }

  @Override
  public void initializePlayerVictories(GameImpl game) {
    ws.initializePlayerVictories(game);
  }
}
