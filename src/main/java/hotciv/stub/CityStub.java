package hotciv.stub;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.standard.CityImpl;

public class CityStub implements City {

  private Player owner;

  public CityStub(Player owner){
  this.owner = owner;
  }

  @Override
  public Player getOwner() { return Player.RED;}

  @Override
  public int getSize() {return 42;}

  @Override
  public int getTreasury() { return 0; }

  @Override
  public String getProduction() { return null; }

  @Override
  public String getWorkforceFocus() { return null;}

  @Override
  public String getId() {
    return "5";
  }
}
