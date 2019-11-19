package hotciv.standard.Broker.Proxies;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.Tile;
import hotciv.standard.Broker.BrokerConstants;

public class TileProxy implements Tile, ClientProxy {
  private Requestor requestor;

  public TileProxy(Requestor requestor) {
    this.requestor = requestor;
  }

  @Override
  public String getTypeString() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.tileId, BrokerConstants.tileString, String.class );
  }


}
