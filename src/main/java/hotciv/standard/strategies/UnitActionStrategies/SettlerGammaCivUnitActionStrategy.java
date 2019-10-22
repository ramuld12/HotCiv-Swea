package hotciv.standard.strategies.UnitActionStrategies;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public class SettlerGammaCivUnitActionStrategy implements UnitActionStrategy {
  @Override
  public void performUnitActionAt(GameImpl game, Position settlerPosition) {
    game.removeUnitFromUnitsMapAtPosition(settlerPosition);
    game.createCityAtPosition(settlerPosition);
  }

  }
