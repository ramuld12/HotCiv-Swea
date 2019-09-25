package hotciv.standard.strategies;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public class AlphaCivBattleStrategy implements BattleStrategy {


  @Override
  public boolean battle(GameImpl game, Position attacking, Position defending) {
    return false;
  }
}
