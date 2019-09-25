package hotciv.standard.strategies;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

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


    boolean isAttackingUnitInACity = cities.containsKey(attackingPosition);
    boolean isDefendingUnitInACity = cities.containsKey(defendingPosition);
    boolean isAttackingUnitOnHill = world.get(attackingPosition).getTypeString().equals(GameConstants.HILLS);
    boolean isDefendingUnitOnHill = world.get(defendingPosition).getTypeString().equals(GameConstants.HILLS);
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
