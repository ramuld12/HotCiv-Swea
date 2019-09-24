package hotciv.standard.strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class BetaCivWinningStrategy implements WinningStrategy {
  @Override
  public Player getWinner(GameImpl game) {
    if (game.doesPlayerInTurnOwnAllCities()) {
      return game.getPlayerInTurn();
    }
    return null;
  }
}


