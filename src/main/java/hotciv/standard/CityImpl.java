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

  public void increaseTreas() {
    treas += 6;
  }

  public void changeProduction(String unitType){
    prod = unitType;
  }

  public void reduceTreasury(int amount) {
    treas -= amount;
  }

  public int getProdCost() {
    if (prod.equals(GameConstants.ARCHER)) {return 10;}
    else if (prod.equals(GameConstants.LEGION)) {return 15;}
    else if (prod.equals(GameConstants.SETTLER)) {return 30;}
    return 0;
  }

  public boolean hasEnoughTreasure() {
    if (treas >= getProdCost()){
      return true;
    }
    return false;
  }

  public void changeOwner(Player playerInTurn) {
    this.p = playerInTurn;
  }
}
