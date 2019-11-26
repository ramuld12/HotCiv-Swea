package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {
  private int defenseStrength;
  private int attackStrength;
  private String type;
  private Player owner;
  private int moveCounter;
  private boolean isMovable;
  private boolean isFortified;

  public UnitImpl(String type, Player owner) {
    this.type = type;
    this.owner = owner;
    this.moveCounter = 1;
    this.isMovable = true;
    if (type.equals(GameConstants.ARCHER)) {
      this.defenseStrength = 3;
      this.attackStrength = 2;
      this.isFortified = false;
    }
    if (type.equals(GameConstants.LEGION)) {
      this.defenseStrength = 2;
      this.attackStrength = 4;
    }
    if (type.equals(GameConstants.SETTLER)) {
      this.defenseStrength = 3;
      this.attackStrength = 0;
    }
    if (type.equals(GameConstants.B52)) {
      this.defenseStrength = 8;
      this.attackStrength = 1;
      this.moveCounter = 2;
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
    return moveCounter;
  }

  @Override
  public int getDefensiveStrength() {
    return defenseStrength;
  }

  public void fortifyArcher() {
    boolean isArcherAlreadyFortified = isFortified;

    if (isArcherAlreadyFortified) {
      isMovable = true;
      defenseStrength /= 2;
    } else {
      isFortified = true;
      isMovable = false;
      defenseStrength *= 2;
    }
  }


  @Override
  public int getAttackingStrength() {
    return attackStrength;
  }

  @Override
  public boolean isMoveable() {
    return isMovable;
  }

  @Override
  public String getId() {
    return null;
  }

  public void decreaseMoveCount() {
    moveCounter--;
  }

  public void resetMoveCounter() {
    moveCounter = 1;
  }

  public void changeAttackStrength(int newAttackStrength) {
    attackStrength = newAttackStrength;
  }

}

