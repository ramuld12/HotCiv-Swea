package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {
  private String gc;
  private Player owner;

  public UnitImpl(String gc, Player owner) {
    this.gc = gc;
    this.owner = owner;
  }

  @Override
  public String getTypeString() {
    return gc;
  }

  @Override
  public Player getOwner() {
    return owner;
  }

  @Override
  public int getMoveCount() {
    return 0;
  }

  @Override
  public int getDefensiveStrength() {
    return 0;
  }

  @Override
  public int getAttackingStrength() {
    return 0;
  }
}
