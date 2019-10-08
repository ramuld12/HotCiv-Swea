package hotciv.standard.strategies.UnitActionStrategies;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public class SemiCivUnitActionStrategy implements UnitActionStrategy {

  @Override
  public void performUnitActionAt(GameImpl game, Position p) {
    boolean isThereAUnitAtPosition = game.getUnitAt(p) != null;
    String UnitForAction = game.getUnitAt(p).getTypeString();
    if (isThereAUnitAtPosition) {
      if (UnitForAction.equals(GameConstants.SETTLER)) {performSettlerActionAt(game,p);}
    }
  }

  private void performSettlerActionAt(GameImpl game, Position settlerPosition) {
    game.removeUnitFromUnitsMapAtPosition(settlerPosition);
    game.createCityAtPosition(settlerPosition);
  }
}