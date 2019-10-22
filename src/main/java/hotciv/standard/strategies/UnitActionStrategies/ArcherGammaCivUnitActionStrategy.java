package hotciv.standard.strategies.UnitActionStrategies;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public class ArcherGammaCivUnitActionStrategy implements UnitActionStrategy {
  @Override
  public void performUnitActionAt(GameImpl game, Position p)  {
    game.getUnitAt(p).fortifyArcher();
  }
}
