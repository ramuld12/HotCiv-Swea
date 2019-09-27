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
  private HashMap<Position, CityImpl> cities;
  private HashMap<Position, TileImpl> world;


  public void battleTest(GameImpl game, Position attackingPosition, Position defendingPosition) {
    units = game.getUnits();
    UnitImpl attackingUnit = units.get(attackingPosition);
    UnitImpl defendingUnit = units.get(defendingPosition);
    int numberOfFriendlySorroundingAttackUnits = game.findNumberOfFriendlyNeighbourUnits(attackingPosition);
    int numberOfFriendlySorroundingDefenseUnits = game.findNumberOfFriendlyNeighbourUnits(defendingPosition);
    attackingUnitStrength = attackingUnit.getAttackingStrength();
    defenseUnitStrength = defendingUnit.getDefensiveStrength();

    defenseUnitStrength = findBattleStrength(defendingPosition, defenseUnitStrength,numberOfFriendlySorroundingDefenseUnits);
    attackingUnitStrength = findBattleStrength(attackingPosition, attackingUnitStrength, numberOfFriendlySorroundingAttackUnits);
  }

  private int findBattleStrength(Position unitPosition, int initialUnitStrength,int numberOfFriendlySorroundingUnit) {
    int strength = initialUnitStrength;
    boolean isUnitInACity = cities.containsKey(unitPosition);
    boolean isUnitOnHill = world.get(unitPosition).getTypeString().equals(GameConstants.HILLS);

    defenseUnitStrength += numberOfFriendlySorroundingUnit;

    attackingUnitStrength += numberOfFriendlySorroundingUnit;

    if (isUnitInACity){
      strength *= 3;
    }
    if (isUnitOnHill) {
      strength *= 2;    }

    return strength;
  }

  @Override
  public boolean battle(GameImpl game, Position attackingPosition, Position defendingPosition) {
    battleTest(game, attackingPosition, defendingPosition); // Calculating values from the battle
    //attackingUnitStrength *= rollDie();
    //defenseUnitStrength *= rollDie();
    boolean didAttackWin = attackingUnitStrength > defenseUnitStrength;
    if (didAttackWin) {
      incrementNumberOfSuccesfullAttacks(game);
    }
    else {
      units.remove(attackingPosition);
    }
    return didAttackWin;
  }

  public void incrementNumberOfSuccesfullAttacks(GameImpl game){
    HashMap<Player, Integer> players = game.getPlayers();
    Player playerInTurn = game.getPlayerInTurn();
    players.put(playerInTurn, players.get(playerInTurn) + 1 );
  }

  public int rollDie(){
    Random rand = new Random();
    int die = rand.nextInt(6)+1;
    return die;
  }

  public int getAttackingUnitStrength() {return attackingUnitStrength;}

  public int getDefenseUnitStrength() {return defenseUnitStrength;}
}
