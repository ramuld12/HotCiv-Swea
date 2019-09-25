package hotciv.standard.strategies;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.utility.Utility;

import java.util.HashMap;

public class EpsilonCivBattleStrategy implements BattleStrategy {

  @Override
  public void battle(GameImpl game, Position attackingPosition, Position defendingPosition) {
    HashMap<Player, Integer> players = game.getPlayers();
    HashMap<Position,UnitImpl> units = game.getUnits();
    HashMap<Position, CityImpl> cities = game.getCities();
    HashMap<Position, TileImpl> world = game.getWorld();
    Player playerInTurn = game.getPlayerInTurn();
    UnitImpl attackingUnit = units.get(attackingPosition);
    UnitImpl defendingUnit = units.get(defendingPosition);
    int numberOfFriendlySorroundingAttackUnits = game.findNumberOfFriendlyNeighbourUnits(attackingPosition);
    int numberOfFriendlySorroundingDefenseUnits = game.findNumberOfFriendlyNeighbourUnits(defendingPosition);
    int attackingUnitStrength =  attackingUnit.getAttackingStrength();
    int defenseUnitStrength = defendingUnit.getDefensiveStrength();


    boolean isAttackingUnitInACity = cities.containsKey(attackingPosition);
    boolean isDefendingUnitInACity = cities.containsKey(defendingPosition);
    boolean isAttackingUnitOnHill = world.get(attackingPosition).getTypeString().equals(GameConstants.HILLS);
    boolean isDefendingUnitOnHill = world.get(defendingPosition).getTypeString().equals(GameConstants.HILLS);

    defendingUnit.changeDefenseStrength((defenseUnitStrength + numberOfFriendlySorroundingDefenseUnits));

    attackingUnit.changeAttackStrength(attackingUnitStrength + numberOfFriendlySorroundingAttackUnits);

    if (isAttackingUnitInACity) {
      attackingUnit.changeAttackStrength(attackingUnit.getAttackingStrength() * 3);
    }
    if (isDefendingUnitInACity){
      defendingUnit.changeDefenseStrength(defendingUnit.getDefensiveStrength() * 3);
    }
    if (isAttackingUnitOnHill) {
      attackingUnit.changeAttackStrength(attackingUnit.getAttackingStrength() * 2);
    }
    if (isDefendingUnitOnHill) {
      defendingUnit.changeDefenseStrength(defendingUnit.getDefensiveStrength() * 2);
    }




    players.put(playerInTurn, players.get(playerInTurn) + 1 );
  }

}
