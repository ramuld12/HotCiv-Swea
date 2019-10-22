package hotciv.standard.strategies.UnitActionStrategies;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;


public class ThetaCivUnitActionStrategy implements UnitActionStrategy {
  @Override
  public void performUnitActionAt(GameImpl game, Position p) {
    boolean isThereAUnitAtPosition = game.getUnitAt(p) != null;
    String UnitForAction = game.getUnitAt(p).getTypeString();
    if (isThereAUnitAtPosition) {
      if (UnitForAction.equals(GameConstants.ARCHER)) {new ArcherGammaCivUnitActionStrategy().performUnitActionAt(game,p);}
      else if (UnitForAction.equals(GameConstants.SETTLER)) {new SettlerGammaCivUnitActionStrategy().performUnitActionAt(game,p);}
      else if (UnitForAction.equals(GameConstants.B52)) {performB52ActionAt(game,p);}
    }
  }

  private void performB52ActionAt(GameImpl game, Position actionPosition) {
    boolean isThereACity = game.getCityAt(actionPosition) != null;
    boolean isThereForrest = game.getTileAt(actionPosition).getTypeString().equals(GameConstants.FOREST);
    if (isThereACity) {
      game.getCityAt(actionPosition).decreasePopulationSize();
      if (game.getCityAt(actionPosition).getPopulationSize() == 0){
        game.removeCityFromCitiesMapAtPosition(actionPosition);
      }
    }
    if (isThereForrest) {
      game.removeTileFromWorldMapAtPosition(actionPosition);
      game.createTileAtPosition(actionPosition,new TileImpl(GameConstants.PLAINS));
    }
  }

}