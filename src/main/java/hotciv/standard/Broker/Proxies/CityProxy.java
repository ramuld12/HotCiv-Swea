package hotciv.standard.Broker.Proxies;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerConstants;

import java.util.UUID;

public class CityProxy implements City, ClientProxy {

  private String id;
  private Requestor requestor;

  public CityProxy(String id, Requestor requestor){
    this.requestor = requestor;
    this.id = UUID.randomUUID().toString();
  }

  @Override
  public Player getOwner() {
    //System.out.println(id);
    return requestor.sendRequestAndAwaitReply(id,BrokerConstants.getOwnerString,Player.class);
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

  @Override
  public String getId() {
    return id;
  }
}
