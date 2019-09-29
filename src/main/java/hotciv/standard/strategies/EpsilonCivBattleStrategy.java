package hotciv.standard.strategies;

import hotciv.framework.*;
import hotciv.standard.*;

import java.util.HashMap;

public class EpsilonCivBattleStrategy implements BattleStrategy {
  private DieStrategy dieStrategy;
  private int attackingUnitStrength;
  private int defenseUnitStrength;
  private HashMap<Position, UnitImpl> units;
  private HashMap<Position, CityImpl> cities;
  private HashMap<Position, TileImpl> world;
  private HashMap<Player, Integer> playerWins;
  private GameImpl game;

  public EpsilonCivBattleStrategy(DieStrategy dieStrategy) {
    this.dieStrategy = dieStrategy;
  }

  public void battleTest(GameImpl game, Position attackingPosition, Position defendingPosition) {
    units = game.getUnits();
    cities = game.getCities();
    world = game.getWorld();
    playerWins = game.getPlayers();
    this.game = game;
    UnitImpl attackingUnit = units.get(attackingPosition);
    UnitImpl defendingUnit = units.get(defendingPosition);
    attackingUnitStrength = attackingUnit.getAttackingStrength();
    defenseUnitStrength = defendingUnit.getDefensiveStrength();

    attackingUnitStrength = findBattleStrength(attackingPosition, attackingUnitStrength);
    defenseUnitStrength = findBattleStrength(defendingPosition, defenseUnitStrength);
  }



  @Override
  public boolean battle(GameImpl game, Position attackingPosition, Position defendingPosition) {
    battleTest(game, attackingPosition, defendingPosition); // Calculating values from the battle
    attackingUnitStrength *= dieStrategy.die();
    defenseUnitStrength *= dieStrategy.die();
    boolean didAttackWin = attackingUnitStrength > defenseUnitStrength;
    if (didAttackWin) {
      incrementNumberOfSuccesfulAttacks(game);
    }
    else {
      units.remove(attackingPosition);
    }
    return didAttackWin;
  }

  private void incrementNumberOfSuccesfulAttacks(GameImpl game){
    Player playerInTurn = game.getPlayerInTurn();
    playerWins.put(playerInTurn, playerWins.get(playerInTurn) + 1 );
  }

  /**
   * Finds the battle strength of a unit at a given position
   * @param unitPosition The position of the unit
   * @param initialUnitStrength The initial strength of the unit
   * @return The battleStrength of the unit before diceroll
   */
  private int findBattleStrength(Position unitPosition, int initialUnitStrength) {
    int strength = initialUnitStrength;
    int numberOfFriendlySorroundingUnits = game.findNumberOfFriendlyNeighbourUnits(unitPosition);
    boolean isUnitInACity = cities.containsKey(unitPosition);
    boolean isUnitOnHill = world.get(unitPosition).getTypeString().equals(GameConstants.HILLS);

    strength += numberOfFriendlySorroundingUnits;
    if (isUnitInACity){
      strength *= 3;
    }
    if (isUnitOnHill) {
      strength *= 2;    }

    return strength;
  }

  public int getAttackingUnitStrength() {return attackingUnitStrength;}

  public int getDefenseUnitStrength() {return defenseUnitStrength;}
}
