package hotciv.standard.Broker.Proxies;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerConstants;

public class CityProxy implements City, ClientProxy {

  private Requestor requestor;

  public CityProxy(Requestor requestor){ this.requestor = requestor;}


  @Override
  public Player getOwner() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.cityId,BrokerConstants.getOwnerString,Player.class);
  }

  @Override
  public int getSize() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.cityId,BrokerConstants.getSizeString,Integer.class);
  }

  @Override
  public int getTreasury() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.cityId,BrokerConstants.getTreasuryString,Integer.class);
  }

  @Override
  public String getProduction() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.cityId,BrokerConstants.getProductionString,String.class);
  }

  @Override
  public String getWorkforceFocus() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.cityId,BrokerConstants.getWorkforceFocus,String.class);
  }
}