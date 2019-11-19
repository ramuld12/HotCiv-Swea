package hotciv.standard.Broker.BrokerStubs;

import frds.broker.Servant;
import hotciv.framework.GameConstants;
import hotciv.framework.Tile;

public class StubTileBroker implements Tile, Servant {
  @Override
  public String getTypeString() {
    return GameConstants.PLAINS;
  }
}
