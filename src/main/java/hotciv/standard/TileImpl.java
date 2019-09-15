package hotciv.standard;

import hotciv.framework.*;
public class TileImpl implements Tile {

  private String gc;

  public TileImpl(String gc) {
    this.gc = gc;
  }

  @Override
  public String getTypeString() {
    return gc;
  }

  public Boolean isNotValidMovementTile () {
    if (gc.equals(GameConstants.OCEANS) || gc.equals(GameConstants.MOUNTAINS)) {return true;}
    return false;
  }
}
