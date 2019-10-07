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

  public Boolean isValidMovementTileType (UnitImpl unit) {
    if (unit.getTypeString().equals(GameConstants.B52))
      return true;
    return !gc.equals(GameConstants.OCEANS) && !gc.equals(GameConstants.MOUNTAINS);
  }
}
