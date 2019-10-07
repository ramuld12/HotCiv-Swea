package hotciv.standard.strategies.UnitActionStrategies;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;

import java.util.HashMap;

public class ThetaCivUnitActionStrategy implements UnitActionStrategy {
  @Override
  public void performUnitActionAt(GameImpl game, Position p) {
    boolean isThereAUnitAtPosition = game.getUnitAt(p) != null;
    String UnitForAction = game.getUnitAt(p).getTypeString();
    if (isThereAUnitAtPosition) {
      if (UnitForAction.equals(GameConstants.ARCHER)) {performArcherActionAt(game,p);}
      else if (UnitForAction.equals(GameConstants.SETTLER)) {performSettlerActionAt(game,p);}
      else if (UnitForAction.equals(GameConstants.B52)) {performB52ActionAt(game,p);}
    }
  }

  private void performB52ActionAt(GameImpl game, Position actionPoistion) {
    boolean isThereACity = game.getCityAt(actionPoistion) != null;
    boolean isThereForrest = game.getTileAt(actionPoistion).equals(GameConstants.FOREST);
    if (isThereACity) {
      game.getCityAt(actionPoistion).decreasePopulationSize();
      if (game.getCityAt(actionPoistion).getPopulationSize() == 0){
        game.removeCityFromCitiesMapAtPosition(actionPoistion);
      }
    }
    if (isThereForrest) {
      game.removeTileFromWorldMapAtPosition(actionPoistion);
      game.createTileAtPosition(actionPoistion,new TileImpl(GameConstants.PLAINS));
    }
  }

  private void performArcherActionAt(GameImpl game, Position p) {
    game.getUnitAt(p).fortifyArcher();
  }

  private void performSettlerActionAt(GameImpl game, Position settlerPosition) {
    game.removeUnitFromUnitsMapAtPosition(settlerPosition);
    game.createCityAtPosition(settlerPosition);
  }
}