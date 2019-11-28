package hotciv.standard.Broker.Proxies;

import frds.broker.Requestor;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.standard.Broker.BrokerConstants;

public class UnitProxy implements Unit {
  private String id;
  private Requestor requestor;

  public UnitProxy(String id, Requestor requestor) {
    this.requestor = requestor;
    this.id = id;
  }

  @Override
  public String getTypeString() {
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getTypeString, String.class);
  }

  @Override
  public Player getOwner() {
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getOwnerUnit, Player.class);
  }

  @Override
  public int getMoveCount() {
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getMoveCountString, Integer.class);
  }

  @Override
  public int getDefensiveStrength() {
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getDefensiveStrengthString, Integer.class);
  }

  @Override
  public int getAttackingStrength() {
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getAttackingStrengthString, Integer.class);
  }

  @Override
  public boolean isMoveable() {
    return false;
  }

  @Override
  public String getId() {
    return id;
  }
}
