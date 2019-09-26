package hotciv.standard.strategies;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.utility.Utility;
import java.util.Random;

import java.util.HashMap;

public class EpsilonCivBattleStrategy implements BattleStrategy {
  private int attackingUnitStrength;
  private int defenseUnitStrength;
  private HashMap<Position, UnitImpl> units;

  public void battleTest(GameImpl game, Position attackingPosition, Position defendingPosition) {
    units = game.getUnits();
    HashMap<Position, CityImpl> cities = game.getCities();
    HashMap<Position, TileImpl> world = game.getWorld();
    UnitImpl attackingUnit = units.get(attackingPosition);
    UnitImpl defendingUnit = units.get(defendingPosition);
    int numberOfFriendlySorroundingAttackUnits = game.findNumberOfFriendlyNeighbourUnits(attackingPosition);
    int numberOfFriendlySorroundingDefenseUnits = game.findNumberOfFriendlyNeighbourUnits(defendingPosition);
    attackingUnitStrength = attackingUnit.getAttackingStrength();
    defenseUnitStrength = defendingUnit.getDefensiveStrength();


    boolean isAttackingUnitInACity = cities.containsKey(attackingPosition);
    boolean isDefendingUnitInACity = cities.containsKey(defendingPosition);
    boolean isAttackingUnitOnHill = world.get(attackingPosition).getTypeString().equals(GameConstants.HILLS);
    boolean isDefendingUnitOnHill = world.get(defendingPosition).getTypeString().equals(GameConstants.HILLS);

    defenseUnitStrength += numberOfFriendlySorroundingDefenseUnits;

    attackingUnitStrength += numberOfFriendlySorroundingAttackUnits;

    if (isAttackingUnitInACity) {
      attackingUnitStrength *= 3;
    }
    if (isDefendingUnitInACity){
      defenseUnitStrength *= 3;
    }
    if (isAttackingUnitOnHill) {
      attackingUnitStrength *= 2;
    }
    if (isDefendingUnitOnHill) {
      defenseUnitStrength *= 2;    }
  }

  @Override
  public boolean battle(GameImpl game, Position attackingPosition, Position defendingPosition) {
    HashMap<Player, Integer> players = game.getPlayers();
    Player playerInTurn = game.getPlayerInTurn();
    Random die = new Random();
    //int d1 = die.nextInt(6)+1;
    //int d2 = die.nextInt(6)+1;


    battleTest(game, attackingPosition, defendingPosition);
    //attackingUnitStrength *= d1;
    //defenseUnitStrength *= d2;
    boolean didAttackWin = attackingUnitStrength > defenseUnitStrength;
    if (didAttackWin) {
      players.put(playerInTurn, players.get(playerInTurn) + 1 );
    }
    else {
      units.remove(attackingPosition);
    }


    return didAttackWin;
  }

  public int getAttackingUnitStrength() {return attackingUnitStrength;}

  public int getDefenseUnitStrength() {return defenseUnitStrength;}
}
