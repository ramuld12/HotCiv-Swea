package hotciv.standard.Broker.Proxies;

import frds.broker.Requestor;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.standard.Broker.BrokerConstants;

public class UnitProxy implements Unit {
  private Requestor requestor;

  public UnitProxy(Requestor requestor) {
    this.requestor = requestor;
  }

  @Override
  public String getTypeString() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.unitId,BrokerConstants.getTypeStringString, String.class);
  }

  @Override
  public Player getOwner() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.unitId,BrokerConstants.getOwnerString, Player.class);
  }

  @Override
  public int getMoveCount() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.unitId,BrokerConstants.getMoveCountString, Integer.class);
  }

  @Override
  public int getDefensiveStrength() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.unitId,BrokerConstants.getDefensiveStrengthString, Integer.class);
  }

  @Override
  public int getAttackingStrength() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.unitId,BrokerConstants.getAttackingStrengthString, Integer.class);
  }

  @Override
  public boolean isMoveable() {
    return false;
  }
}
