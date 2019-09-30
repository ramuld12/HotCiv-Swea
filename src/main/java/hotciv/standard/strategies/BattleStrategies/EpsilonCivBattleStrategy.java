package hotciv.standard.strategies.BattleStrategies;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.standard.strategies.DieStrategies.DieStrategy;

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

  @Override
  public boolean battle(GameImpl game, Position attackingPosition, Position defendingPosition) {
    units = game.getUnits();
    cities = game.getCities();
    world = game.getWorld();
    playerWins = game.getPlayers();
    this.game = game;
    return didAttackWin(attackingPosition, defendingPosition);
  }

  private boolean didAttackWin (Position attackingPosition, Position defendingPosition) {
    findBattlingUnitsStrengths(attackingPosition, defendingPosition);
    boolean didAttackWin = attackingUnitStrength > defenseUnitStrength;
    if (didAttackWin) {
      incrementNumberOfSuccesfulAttacks();
    }
    else {
      units.remove(attackingPosition);
    }
    return didAttackWin;
  }

  /**
   * Finds the battleStrengths of the battling units
   * @param attackingPosition position of attacking unit
   * @param defendingPosition position of defending unit
   */
  private void findBattlingUnitsStrengths(Position attackingPosition, Position defendingPosition) {
    attackingUnitStrength = findBattleStrength(attackingPosition, units.get(attackingPosition).getAttackingStrength());
    defenseUnitStrength = findBattleStrength(defendingPosition, units.get(defendingPosition).getDefensiveStrength());
  }

  private void incrementNumberOfSuccesfulAttacks(){
    Player playerInTurn = game.getPlayerInTurn();
    playerWins.put(playerInTurn, playerWins.get(playerInTurn) + 1 );
  }

  /**
   * Finds the battle strength of a single unit at a given position
   * @param unitPosition The position of the unit
   * @param initialUnitStrength The initial strength of the unit
   * @return The battleStrength of the unit
   */
  private int findBattleStrength(Position unitPosition, int initialUnitStrength) {
    int strength = initialUnitStrength;
    int numberOfFriendlySorroundingUnits = game.findNumberOfFriendlyNeighbourUnits(unitPosition);
    boolean isUnitInACity = cities.containsKey(unitPosition);
    boolean isUnitOnHill = world.get(unitPosition).getTypeString().equals(GameConstants.HILLS);

    strength += numberOfFriendlySorroundingUnits;
    if (isUnitInACity){ strength *= 3; }
    if (isUnitOnHill) { strength *= 2; }

    return strength * dieStrategy.die();
  }

  public int getAttackingUnitStrength() {return attackingUnitStrength;}

  public int getDefenseUnitStrength() {return defenseUnitStrength;}
}
