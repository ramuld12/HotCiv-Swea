package hotciv.standard.Broker.Proxies;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Tile;
import hotciv.standard.Broker.BrokerConstants;

public class TileProxy implements Tile, ClientProxy {
  private final String id;
  private Requestor requestor;

  public TileProxy(String id, Requestor requestor) {
    this.requestor = requestor;
    this.id = id;
  }

  @Override
  public String getTypeString() {
    System.out.println(id);
    return requestor.sendRequestAndAwaitReply(id, BrokerConstants.tileString, String.class );
  }

  @Override
  public String getId() {
    return id;
  }


}
