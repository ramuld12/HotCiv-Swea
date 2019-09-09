package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

public class CityImpl implements City {

  private Player p;
  private int treas;

  public CityImpl(Player p) {
    this.p = p;
  }

  @Override
  public Player getOwner() {
    return p;
  }

  @Override
  public int getSize() {
    return 0;
  }//Not done

  @Override
  public int getTreasury() {
    return treas;
  }

  @Override
  public String getProduction() {
    return null;
  }//Not done

  @Override
  public String getWorkforceFocus() {
    return null;
  }//Not done

  public void incrementTreas() {
    this.treas += 6;
  }

}
