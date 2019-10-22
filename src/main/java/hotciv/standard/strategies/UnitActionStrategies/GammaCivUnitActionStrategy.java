package hotciv.standard.strategies.UnitActionStrategies;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.standard.strategies.UnitActionStrategies.UnitActionStrategy;

public class GammaCivUnitActionStrategy implements UnitActionStrategy {

  @Override
  public void performUnitActionAt(GameImpl game, Position p) {
    boolean isThereAUnitAtPosition = game.getUnitAt(p) != null;
    String UnitForAction = game.getUnitAt(p).getTypeString();
    if (isThereAUnitAtPosition) {
      if (UnitForAction.equals(GameConstants.ARCHER)) {new ArcherGammaCivUnitActionStrategy().performUnitActionAt(game,p);}
      else if (UnitForAction.equals(GameConstants.SETTLER)) {new SettlerGammaCivUnitActionStrategy().performUnitActionAt(game,p);}
    }
  }
}
