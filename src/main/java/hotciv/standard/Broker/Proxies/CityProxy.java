package hotciv.standard.Broker.Proxies;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerConstants;

public class CityProxy implements City, ClientProxy {

  private String id;
  private Requestor requestor;

  public CityProxy(String id, Requestor requestor){
    this.requestor = requestor;
    this.id = id;
  }

  @Override
  public Player getOwner() {
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getOwnerCity,Player.class);
  }

  @Override
  public int getSize() {
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getSizeString,Integer.class);
  }

  @Override
  public int getTreasury() {
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getTreasuryString,Integer.class);
  }

  @Override
  public String getProduction() {
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getProductionString,String.class);
  }

  @Override
  public String getWorkforceFocus() {
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getWorkforceFocus,String.class);
  }

  @Override
  public String getId() {
    return id;
  }
}
