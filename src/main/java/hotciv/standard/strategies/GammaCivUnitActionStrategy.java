package hotciv.standard.strategies;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public class GammaCivUnitActionStrategy implements UnitActionStrategy {

  @Override
  public void performUnitActionAt(GameImpl game, Position p) {
    boolean isThereAUnitAtPosition = game.getUnitAt(p) != null;
    if (isThereAUnitAtPosition) {
      String UnitForAction = game.getUnitAt(p).getTypeString();
      if (UnitForAction.equals(GameConstants.ARCHER)) {performArcherActionAt(game,p);}
      else if (UnitForAction.equals(GameConstants.SETTLER)) {performSettlerActionAt(game,p);}
    }

  }

  public void performArcherActionAt(GameImpl game, Position p) {

  }

  public void performSettlerActionAt(GameImpl game, Position settlerPosition) {
    game.removeUnitFromUnitsMapAtPosition(settlerPosition);
    game.createCityAtPosition(settlerPosition);
  }
}
