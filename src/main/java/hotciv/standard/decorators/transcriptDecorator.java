package hotciv.standard.decorators;

import hotciv.framework.*;
import hotciv.standard.HotCivFactory.HotCivFactory;

public class transcriptDecorator implements Game {
  private HotCivFactory factory;
  public transcriptDecorator (HotCivFactory hcf) {
    factory = hcf;
  }

  @Override
  public Tile getTileAt(Position p) {
    return null;
  }

  @Override
  public Unit getUnitAt(Position p) {
    return null;
  }

  @Override
  public City getCityAt(Position p) {
    return null;
  }

  @Override
  public Player getPlayerInTurn() {
    return null;
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public int getAge() {
    return 0;
  }

  @Override
  public boolean moveUnit(Position from, Position to) {
    return false;
  }

  @Override
  public void endOfTurn() {

  }

  @Override
  public void changeWorkForceFocusInCityAt(Position p, String balance) {

  }

  @Override
  public void changeProductionInCityAt(Position p, String unitType) {

  }

  @Override
  public void performUnitActionAt(Position p) {

  }
}
