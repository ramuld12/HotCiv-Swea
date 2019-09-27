package hotciv.standard.strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class ZetaCivWInningStrategy implements WinningStrategy {
  private WinningStrategy ws = new BetaCivWinningStrategy();
  private int numberOfRounds;


  @Override
  public Player getWinner(GameImpl game) {
    numberOfRounds = game.getRoundNumber();
    if (numberOfRounds < 20) {
      return ws.getWinner(game);
    }
    ws = new EpsilonCivWinningStrategy();
    return ws.getWinner(game);
  }

  @Override
  public void initializePlayerVictories(GameImpl game) {

  }
}
