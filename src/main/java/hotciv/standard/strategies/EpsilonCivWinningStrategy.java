package hotciv.standard.strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import java.util.HashMap;

public class EpsilonCivWinningStrategy implements WinningStrategy {

  @Override
  public void initializePlayerVictories(GameImpl game) {
    HashMap<Player, Integer> playerVictories = game.getPlayers();
    playerVictories.put(Player.RED, 0);
    playerVictories.put(Player.BLUE, 0);
  }

  @Override
  public Player getWinner(GameImpl game) {
    boolean isPlayerInTurnTheWinner = game.getVictoriesForPlayer(game.getPlayerInTurn()) == 3;
    if (isPlayerInTurnTheWinner) {
      return game.getPlayerInTurn();
    }
    return null;
  }
}
