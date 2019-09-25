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
    int numberOfFriendlySorroundingAttackUnits = 0;
    int numberOfFriendlySorroundingDefenseUnits = 0;


    boolean isAttackingUnitInACity = cities.containsKey(attackingPosition);
    boolean isDefendingUnitInACity = cities.containsKey(defendingPosition);
    boolean isAttackingUnitOnHill = world.get(attackingPosition).getTypeString().equals(GameConstants.HILLS);
    boolean isDefendingUnitOnHill = world.get(defendingPosition).getTypeString().equals(GameConstants.HILLS);

    for (Position friendlyUnitPosition : Utility.get8neighborhoodOf(defendingPosition)) {
      boolean isThereAUnitAtNeighbourPosition = game.getUnitAt(friendlyUnitPosition) != null;
      boolean isFriendlyUnit = isThereAUnitAtNeighbourPosition && game.getUnitAt(friendlyUnitPosition).getOwner().equals(game.getUnitAt(defendingPosition).getOwner());
      if(isFriendlyUnit) {
        numberOfFriendlySorroundingDefenseUnits ++;
      }
    }
    defendingUnit.changeDefenseStrength((defendingUnit.getDefensiveStrength() + numberOfFriendlySorroundingDefenseUnits));

    for (Position friendlyUnitPosition : Utility.get8neighborhoodOf(attackingPosition)) {
      boolean isThereAUnitAtNeighbourPosition = game.getUnitAt(friendlyUnitPosition) != null;
      boolean isFriendlyUnit = isThereAUnitAtNeighbourPosition && game.getUnitAt(friendlyUnitPosition).getOwner().equals(game.getUnitAt(attackingPosition).getOwner());
      if(isFriendlyUnit) {
        numberOfFriendlySorroundingAttackUnits ++;
      }
    }
    attackingUnit.changeAttackStrength(attackingUnit.getAttackingStrength() + numberOfFriendlySorroundingAttackUnits);

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
