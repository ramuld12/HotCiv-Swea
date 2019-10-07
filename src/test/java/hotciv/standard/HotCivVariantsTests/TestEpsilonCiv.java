package hotciv.standard.HotCivVariantsTests;

import hotciv.framework.*;

import hotciv.standard.FixedStrategies.FixedDieStrategyImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TestStubs.FixedEpsilonCivFactory;
import hotciv.standard.TestUtility;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.strategies.BattleStrategies.EpsilonCivBattleStrategy;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

// Skeleton class for EpsilonCiv test cases


public class TestEpsilonCiv {
  private GameImpl game;
  private HashMap<Position, UnitImpl> units;
  private FixedDieStrategyImpl fixedDieStrategy = new FixedDieStrategyImpl();
  private EpsilonCivBattleStrategy battleStrategy = new EpsilonCivBattleStrategy(fixedDieStrategy);
  private TestUtility util;

  @Before
  public void setUp() {
    game = new GameImpl(new FixedEpsilonCivFactory());
    assertThat(game, is(notNullValue()));
    util = new TestUtility(game);
    units = game.getUnits();
  }

  @Test
  public void redAndBlueShouldStartWith0Victories() {
    assertThat(game.getVictoriesForPlayer(Player.RED), is(0));
    assertThat(game.getVictoriesForPlayer(Player.BLUE), is(0));
  }

  @Test
  public void redVictoyCountShouldIncrementWhenWinning() {
    Position redArcher = new Position(2, 0);
    Position blueLegion = new Position(3,2);
    game.moveUnit(redArcher, new Position(3,1));
    util.endOfRound();
    game.getUnitAt(new Position(3,1)).changeAttackStrength(20);
    game.moveUnit(new Position(3,1), blueLegion);
    assertThat(game.getVictoriesForPlayer(Player.RED), is(1));
  }

  @Test
  public void blueVictoryCountShouldIncrementWhenWinning() {
    game.endOfTurn(); // So blue is in turn
    Position redArcherPos = new Position(2, 0);
    Position blueLegionPos = new Position(3,2);
    Position pos3_1 = new Position(3,1);
    util.removeNeighbours(redArcherPos);
    game.moveUnit(blueLegionPos, pos3_1);
    util.endOfRound();
    game.getUnitAt(pos3_1).changeAttackStrength(20);
    game.moveUnit(pos3_1, redArcherPos);
    assertThat(game.getVictoriesForPlayer(Player.BLUE), is(1));
  }

  @Test
  public void redShouldWinWhenVictoryCountReach3(){
    units.put(new Position(3,1), new UnitImpl(GameConstants.SETTLER,Player.BLUE));
    units.put(new Position(2,1), new UnitImpl(GameConstants.SETTLER,Player.BLUE));
    game.getUnitAt(new Position(2,0)).changeAttackStrength(20);
    game.moveUnit(new Position(2,0), new Position(2,1));
    util.endOfRound();
    game.getUnitAt(new Position(2,1)).changeAttackStrength(20);
    game.moveUnit(new Position(2,1),new Position(3,1));
    util.endOfRound();
    game.getUnitAt(new Position(3,1)).changeAttackStrength(20);
    game.moveUnit(new Position(3,1), new Position(3,2));
    assertThat(game.getWinner(), is(Player.RED));
  }

  //Blue Unit wins against 3 red units
  @Test
  public void blueShouldWinWhenVictoryCountReach3(){
    game.endOfTurn();
    units.put(new Position(3,1), new UnitImpl(GameConstants.SETTLER,Player.RED));
    units.put(new Position(2,1), new UnitImpl(GameConstants.SETTLER,Player.RED));
    game.getUnitAt(new Position(3,2)).changeAttackStrength(20);
    game.moveUnit(new Position(3,2), new Position(3,1));
    util.endOfRound();
    game.getUnitAt(new Position(3,1)).changeAttackStrength(20);
    game.moveUnit(new Position(3,1),new Position(2,1));
    util.endOfRound();
    game.getUnitAt(new Position(2,1)).changeAttackStrength(20);
    game.moveUnit(new Position(2,1), new Position(2,0));
    assertThat(game.getWinner(), is(Player.BLUE));
  }

  @Test
  public void redArcherInCityShouldHave6AttackStrengthTimesDie(){
    Position attackingUnitInCity = new Position(1,1);
    Position defendingUnit = new Position(2,1);
    util.removeNeighbours(attackingUnitInCity);
    units.put(defendingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    util.endOfRound();
    util.endOfRound();
    battleStrategy.handlingOfAttack(game,attackingUnitInCity,defendingUnit);
    assertThat(battleStrategy.getAttackingUnitStrength(), is(6*fixedDieStrategy.die()));
  }

  @Test
  public void redArcherInCityShouldHave9DefenseStrength(){
    Position defendingUnitInCity  = new Position(1,1);
    Position attackingUnit = new Position(2,1);
    util.removeNeighbours(defendingUnitInCity);
    units.put(attackingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    util.endOfRound();
    util.endOfRound();
    battleStrategy.handlingOfAttack(game,attackingUnit,defendingUnitInCity);
    assertThat(battleStrategy.getDefenseUnitStrength(), is(9*fixedDieStrategy.die()));
  }

  @Test
  public void redLegionInCityShouldHave12AttackStrengthTimesDie(){
    Position attackingUnitInCity = new Position(1,1);
    Position defendingUnit = new Position(2,1);
    util.removeNeighbours(attackingUnitInCity);
    game.changeProductionInCityAt(attackingUnitInCity, GameConstants.LEGION);
    units.put(defendingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    util.endOfRound();
    util.endOfRound();
    util.endOfRound();
    battleStrategy.handlingOfAttack(game,attackingUnitInCity,defendingUnit);
    assertThat(battleStrategy.getAttackingUnitStrength(), is(12*fixedDieStrategy.die()));
  }

  @Test
  public void redLegionInCityShouldHave6DefenseStrengthTimesDie(){
    Position defendingUnitInCity  = new Position(1,1);
    Position attackingUnit = new Position(2,1);
    util.removeNeighbours(defendingUnitInCity);
    game.changeProductionInCityAt(defendingUnitInCity, GameConstants.LEGION);
    units.put(attackingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    util.endOfRound();
    util.endOfRound();
    util.endOfRound();
    battleStrategy.handlingOfAttack(game,attackingUnit,defendingUnitInCity);
    assertThat(battleStrategy.getDefenseUnitStrength(), is(6*fixedDieStrategy.die()));
  }

  @Test
  public void redArcherOnHillShouldHave4AttackTimesDie(){
    Position attackingUnitOnHill = new Position(0,1);
    Position defendingUnit = new Position(0,2);
    units.put(attackingUnitOnHill, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(defendingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    battleStrategy.handlingOfAttack(game, attackingUnitOnHill, defendingUnit);
    assertThat(battleStrategy.getAttackingUnitStrength(), is(4*fixedDieStrategy.die()));
  }

  @Test
  public void redArcherOnHillShouldHave6DefenseTimesDie(){
    Position defendingUnitOnHill = new Position(0,1);
    Position attackingUnit = new Position(0,2);
    units.put(defendingUnitOnHill, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(attackingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    battleStrategy.handlingOfAttack(game, attackingUnit, defendingUnitOnHill);
    assertThat(battleStrategy.getDefenseUnitStrength(), is(6*fixedDieStrategy.die()));
  }

  @Test
  public void redArcherSorroundedBy2FriendlyUnitsShouldHave4AttackTimesDie() {
    Position archerPositionMain = new Position(8,8);
    Position archerPosition2 = new Position(8,7);
    Position archerPosition3 = new Position(7,8);
    Position defendingPosition = new Position(8,9);
    units.put(archerPositionMain, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(defendingPosition, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    battleStrategy.handlingOfAttack(game, archerPositionMain, defendingPosition);
    assertThat(battleStrategy.getAttackingUnitStrength(), is(4*fixedDieStrategy.die()));
  }

  @Test
  public void redArcherSorroundedBy2FriendlyUnitsShouldHave5DefenseTimesDie() {
    Position archerPositionMain = new Position(8,8);
    Position archerPosition2 = new Position(8,7);
    Position archerPosition3 = new Position(7,8);
    Position attackingPosition = new Position(8,9);
    units.put(archerPositionMain, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(attackingPosition, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    battleStrategy.handlingOfAttack(game, attackingPosition, archerPositionMain);
    assertThat(battleStrategy.getDefenseUnitStrength(), is(5*fixedDieStrategy.die()));
  }

  @Test
  public void redArcherInCityWith2FriendsShouldHave12AttackTimesDie(){
    Position archerPositionMain = new Position(8,8);
    Position archerPosition2 = new Position(8,7);
    Position archerPosition3 = new Position(7,8);
    Position defendingPosition = new Position(8,9);
    game.createCityAtPosition(archerPositionMain);

    units.put(archerPositionMain, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(defendingPosition, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    battleStrategy.handlingOfAttack(game, archerPositionMain, defendingPosition);
    assertThat(battleStrategy.getAttackingUnitStrength(), is(12*fixedDieStrategy.die()));
  }

  @Test
  public void redArcherOnHillWith2FriendsShouldHave8AttackTimesDie(){
    Position archerPositionMain = new Position(8,8);
    Position archerPosition2 = new Position(8,7);
    Position archerPosition3 = new Position(7,8);
    Position defendingPosition = new Position(8,9);
    game.createTileAtPosition(archerPositionMain, new TileImpl(GameConstants.HILLS));

    units.put(archerPositionMain, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(defendingPosition, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    battleStrategy.handlingOfAttack(game, archerPositionMain, defendingPosition);
    assertThat(battleStrategy.getAttackingUnitStrength(), is(8*fixedDieStrategy.die()));
  }

  @Test
  public void redArcherOnHillInCityWith2FriendsShouldHave24AttackTimesDie(){
    Position archerPositionMain = new Position(8,8);
    Position archerPosition2 = new Position(8,7);
    Position archerPosition3 = new Position(7,8);
    Position defendingPosition = new Position(8,9);
    game.createTileAtPosition(archerPositionMain, new TileImpl(GameConstants.HILLS));
    game.createCityAtPosition(archerPositionMain);

    units.put(archerPositionMain, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(defendingPosition, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    battleStrategy.handlingOfAttack(game, archerPositionMain, defendingPosition);
    assertThat(battleStrategy.getAttackingUnitStrength(), is(24*fixedDieStrategy.die()));
  }

  @Test
  public void redArcherShouldWin() {
    Position redArcher = new Position(8,8);
    Position redArcher2 = new Position(8,7);
    Position redArcher3 = new Position(7,8);
    Position blueArcher = new Position(8,9);
    game.createTileAtPosition(redArcher, new TileImpl(GameConstants.HILLS));
    game.createCityAtPosition(redArcher);

    units.put(redArcher, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(redArcher2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(redArcher3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(blueArcher, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    assertTrue(battleStrategy.handlingOfAttack(game, redArcher, blueArcher));
  }

  @Test
  public void blueArcherShouldWin() {
    game.endOfTurn();
    Position redArcher = new Position(8,8);
    Position redArcher2 = new Position(8,7);
    Position redArcher3 = new Position(7,8);
    Position blueArcher = new Position(8,9);
    game.createTileAtPosition(redArcher, new TileImpl(GameConstants.HILLS));

    units.put(redArcher, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(redArcher2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(redArcher3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(blueArcher, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    units.get(blueArcher).changeAttackStrength(25);
    assertTrue(battleStrategy.handlingOfAttack(game, blueArcher, redArcher));
  }

  @Test
  public void blueArcherShouldLose() {
    game.endOfTurn();
    Position redArcher = new Position(8,8);
    Position redArcher2 = new Position(8,7);
    Position redArcher3 = new Position(7,8);
    Position blueArcher = new Position(8,9);
    game.createTileAtPosition(redArcher, new TileImpl(GameConstants.HILLS));

    units.put(redArcher, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(redArcher2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(redArcher3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(blueArcher, new UnitImpl(GameConstants.ARCHER,Player.BLUE));;
    assertFalse(battleStrategy.handlingOfAttack(game, blueArcher, redArcher));
  }

  @Test
  public void blueArcherShouldBeRemovedWhenLosing() {
    game.endOfTurn();
    Position redArcher = new Position(8,8);
    Position redArcher2 = new Position(8,7);
    Position redArcher3 = new Position(7,8);
    Position blueArcher = new Position(8,9);

    units.put(redArcher, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(redArcher2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(redArcher3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(blueArcher, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    game.moveUnit(blueArcher, redArcher);
    assertNull(game.getUnitAt(blueArcher));
  }
}

