package hotciv.standard.Broker.Proxies;

import frds.broker.Requestor;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitProxy implements Unit {
  private Requestor requestor;

  public UnitProxy(Requestor requestor) {
    this.requestor = requestor;
  }

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
