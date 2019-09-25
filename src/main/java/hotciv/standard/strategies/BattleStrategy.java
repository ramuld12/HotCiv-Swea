package hotciv.standard.strategies;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

public interface BattleStrategy {

  public boolean battle(GameImpl game, Position attacking, Position defending);
}
