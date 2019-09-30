package hotciv.standard.strategies.BattleStrategies;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.strategies.BattleStrategies.BattleStrategy;

public class AlphaCivBattleStrategy implements BattleStrategy {


  @Override
  public boolean battle(GameImpl game, Position attacking, Position defending) {
    return true;
  }
}
