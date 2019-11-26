package hotciv.standard.Broker.BrokerStubs;

import frds.broker.Servant;
import hotciv.framework.GameConstants;
import hotciv.framework.Tile;

import java.util.UUID;

public class StubTileBroker implements Tile, Servant {
  private final String id = UUID.randomUUID().toString();

  @Override
  public String getTypeString() {
    return GameConstants.PLAINS;
  }

  @Override
  public String getId() {
    return id;
  }
}
