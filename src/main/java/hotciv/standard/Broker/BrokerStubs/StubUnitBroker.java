package hotciv.standard.Broker.BrokerStubs;

import frds.broker.Servant;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class StubUnitBroker implements Unit, Servant {
  @Override
  public String getTypeString() {
    return GameConstants.ARCHER;
  }

  @Override
  public Player getOwner() {
    return Player.RED;
  }

  @Override
  public int getMoveCount() {
    return 42;
  }

  @Override
  public int getDefensiveStrength() {
    return 10;
  }

  @Override
  public int getAttackingStrength() {
    return 10;
  }

  @Override
  public boolean isMoveable() {
    return false;
  }
}
