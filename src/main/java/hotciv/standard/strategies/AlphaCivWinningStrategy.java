package hotciv.standard.strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import java.util.HashMap;

public class AlphaCivWinningStrategy implements WinningStrategy {
  @Override
  public Player getWinner(GameImpl game) {
    if (game.getAge() >= -3000) {return Player.RED;}
    return null;
  }

  @Override
  public void initializePlayerVictories(GameImpl game) {

  }
}
