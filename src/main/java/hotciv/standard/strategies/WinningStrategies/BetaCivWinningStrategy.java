package hotciv.standard.strategies.WinningStrategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import java.util.HashMap;

public class BetaCivWinningStrategy implements WinningStrategy {
  @Override
  public void initializePlayerVictories(GameImpl game) {
  }

  @Override
  public WinningStrategy getCurrentState() {
    return this;
  }

  @Override
  public void changeStateIfNeeded(GameImpl game) {

  }

  @Override
  public Player getWinner(GameImpl game) {
    if (game.doesPlayerInTurnOwnAllCities()) {
      return game.getPlayerInTurn();
    }
    return null;
  }


}


