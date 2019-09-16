package hotciv.standard.strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class AlphaCivWInningStrategy implements WinningStrategy {
  @Override
  public Player getWinner(GameImpl game) {
    if (game.getAge() >= -3000) {return Player.RED;}
    return null;
  }
}
