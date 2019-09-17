package hotciv.standard.strategies;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public interface UnitActionStrategy {

  public void performUnitActionAt(GameImpl game, Position p);

}
