package hotciv.standard.Broker.BrokerStubs;

import frds.broker.Servant;
import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class StubCityBroker implements City, Servant {

  @Override
  public Player getOwner() {
    return Player.RED;
  }

  @Override
  public int getSize() {
    return 42;
  }

  @Override
  public int getTreasury() {
    return 100;
  }

  @Override
  public String getProduction() {
    return GameConstants.ARCHER;
  }

  @Override
  public String getWorkforceFocus() {
    return null;
  }

}
