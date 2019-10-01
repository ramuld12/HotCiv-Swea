package hotciv.standard.strategies.WinningStrategies;

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
  public WinningStrategy getCurrentState() { return this; }

  @Override
  public void changeStateIfNeeded(GameImpl game) { }

  @Override
  public Player getWinner(GameImpl game) {
    boolean isRedTheWinner = game.getVictoriesForPlayer(Player.RED) >= 3;
    boolean isBlueTheWinner = game.getVictoriesForPlayer(Player.BLUE) >= 3;
    if (isRedTheWinner) { return Player.RED; }
    if (isBlueTheWinner) { return Player.BLUE; }
    return null;
  }
}
