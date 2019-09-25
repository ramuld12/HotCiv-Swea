package hotciv.standard.strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import java.util.HashMap;

public class EpsilonCivBattleStrategy implements BattleStrategy {
  @Override
  public void battle(GameImpl game) {
    HashMap<Player, Integer> players = game.getPlayers();
    Player playerInTurn = game.getPlayerInTurn();
    players.put(playerInTurn, players.get(playerInTurn) + 1 );
  }
}
