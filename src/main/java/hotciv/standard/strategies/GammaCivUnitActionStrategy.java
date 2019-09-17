package hotciv.standard.strategies;

import hotciv.framework.*;
import hotciv.standard.*;

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
    game.getUnitAt(p).fortifyArcher();
  }

  public void performSettlerActionAt(GameImpl game, Position settlerPosition) {
    game.removeUnitFromUnitsMapAtPosition(settlerPosition);
    game.createCityAtPosition(settlerPosition);
  }
}
