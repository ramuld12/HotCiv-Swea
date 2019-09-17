package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {
  private int defenseStrength;
  private int attackStrength;
  private String type;
  private Player owner;
  private int move;

  public UnitImpl(String type, Player owner) {
    this.type = type;
    this.owner = owner;
    this.move = 1;
    if (type.equals(GameConstants.ARCHER)) {
      this.defenseStrength = 3;
      this.attackStrength = 2;
    }
    if (type.equals(GameConstants.LEGION)) {
      this.defenseStrength = 2;
      this.attackStrength = 4;
    }
    if (type.equals(GameConstants.SETTLER)) {
      this.defenseStrength = 3;
      this.attackStrength = 0;
    }
  }

  @Override
  public String getTypeString() {
    return type;
  }

  @Override
  public Player getOwner() {
    return owner;
  }

  @Override
  public int getMoveCount() {
    return move;
  }

  @Override
  public int getDefensiveStrength() { return defenseStrength;}

  public void fortifyArcher(){
    defenseStrength *= 2;
  }


  @Override
  public int getAttackingStrength() {
    return attackStrength;
  }

  public void decreaseMoveCount() {
    move --;
  }

  public void resetMoveCounter() {
    move = 1;
  }
}
