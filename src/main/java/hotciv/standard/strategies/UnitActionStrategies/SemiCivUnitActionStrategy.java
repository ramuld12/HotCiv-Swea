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
      if (UnitForAction.equals(GameConstants.SETTLER)) {new SettlerGammaCivUnitActionStrategy().performUnitActionAt(game,p);}
    }
  }
}