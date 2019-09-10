package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class CityImpl implements City {

  private Player p;
  private int treas;
  private String prod;

  public CityImpl(Player p) {
    this.p = p;
    prod = GameConstants.ARCHER;
  }

  @Override
  public Player getOwner() {
    return p;
  }

  @Override
  public int getSize() {
    return 1;
  }

  @Override
  public int getTreasury() {
    return treas;
  }

  @Override
  public String getProduction() {
    return prod;
  }

  @Override
  public String getWorkforceFocus() {
    return null;
  }//Not done

  public void incrementTreas() {
    this.treas += 6;
  }

  public void changeProduction(String unitType){
    prod = unitType;
  }

  public void reduceTreasury(int i) {
    treas -= i;
  }
  public boolean hasEnoughProduction() {
    if (prod.equals(GameConstants.ARCHER) && treas >= 10) {
      return true;
    }
    return false;
  }

}
