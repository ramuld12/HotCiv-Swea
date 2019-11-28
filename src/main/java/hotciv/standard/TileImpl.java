package hotciv.standard;

import hotciv.framework.*;

import java.util.UUID;

public class TileImpl implements Tile {

  private String gc;
  private final String id;

  public TileImpl(String gc) {
    this.gc = gc;
    id = UUID.randomUUID().toString();
  }

  @Override
  public String getTypeString() {
    return gc;
  }

  @Override
  public String getId() {
    return id;
  }

  public Boolean isValidMovementTileType (UnitImpl unit) {
    boolean isThereAUnit = unit != null;
    boolean isUnitB52 = isThereAUnit && unit.getTypeString().equals(GameConstants.B52);
    if (isUnitB52){ return true;}
    return !gc.equals(GameConstants.OCEANS) && !gc.equals(GameConstants.MOUNTAINS);
  }
}
