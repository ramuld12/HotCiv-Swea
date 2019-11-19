package hotciv.standard.Broker.Stubs;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class StubUnit implements Unit {
  @Override
  public String getTypeString() {
    return null;
  }

  @Override
  public Player getOwner() {
    return null;
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

  @Override
  public boolean isMoveable() {
    return false;
  }
}
