package hotciv.standard.strategies;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.utility.Utility;

import java.util.HashMap;

public class EpsilonCivBattleStrategy implements BattleStrategy {
  private int attackingUnitStrength;
  private int defenseUnitStrength;

  public void battleTest(GameImpl game, Position attackingPosition, Position defendingPosition) {
    HashMap<Position,UnitImpl> units = game.getUnits();
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
  public boolean battle(GameImpl game, Position attacking, Position defending) {
    HashMap<Player, Integer> players = game.getPlayers();
    Player playerInTurn = game.getPlayerInTurn();

    battleTest(game, attacking, defending);
    boolean didAttackWin = attackingUnitStrength > defenseUnitStrength;
    if (didAttackWin) {
      players.put(playerInTurn, players.get(playerInTurn) + 1 );
    }
    return didAttackWin;
  }

  public int getAttackingUnitStrength() {return attackingUnitStrength;}

  public int getDefenseUnitStrength() {return defenseUnitStrength;}
}
