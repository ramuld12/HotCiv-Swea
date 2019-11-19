package hotciv.standard.Broker.Stubs;

import hotciv.framework.City;
import hotciv.framework.Player;

public class StubCity implements City {
  @Override
  public Player getOwner() {
    return null;
  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public int getTreasury() {
    return 0;
  }

  @Override
  public String getProduction() {
    return null;
  }

  @Override
  public String getWorkforceFocus() {
    return null;
  }
}
