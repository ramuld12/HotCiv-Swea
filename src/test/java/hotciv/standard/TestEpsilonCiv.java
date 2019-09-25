package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.strategies.*;
import hotciv.utility.Utility;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for EpsilonCiv test cases
 *
 */


public class TestEpsilonCiv {
  private GameImpl game;
  private HashMap<Position, UnitImpl> units;

  /**
   * Fixture for alphaciv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(new AlphaCivAgingStrategy(), new EpsilonCivWinningStrategy(), new AlphaCivUnitActionStrategy(), new AlphaCivWorldLayoutStrategy(), new EpsilonCivBattleStrategy());
    assertThat(game, is(notNullValue()));
    units = game.getUnits();
  }

  /**
   * Method for testing end of round triggers
   */
  public void endOfRound() {
    game.endOfTurn();
    game.endOfTurn();
  }

  private void removeNeighbours(Position p){
    for (Position neighbourPosition : Utility.get8neighborhoodOf(p)) {
      boolean isUnitPresent = game.getUnitAt(neighbourPosition) != null;
      if(isUnitPresent){
        units.remove(neighbourPosition);
      }
    }
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
    endOfRound();
    game.getUnitAt(new Position(3,1)).changeAttackStrength(20);
    game.moveUnit(new Position(3,1), blueLegion);
    assertThat(game.getVictoriesForPlayer(Player.RED), is(1));
  }

  @Test
  public void blueVictoyCountShouldIncrementWhenWinning() {
    game.endOfTurn(); // So blue is in turn
    Position redArcher = new Position(2, 0);
    Position blueLegion = new Position(3,2);
    removeNeighbours(redArcher);
    game.moveUnit(blueLegion, new Position(3,1));
    endOfRound();
    game.moveUnit(new Position(3,1), redArcher);
    assertThat(game.getVictoriesForPlayer(Player.BLUE), is(1));
  }

  @Test
  public void redShouldWinWhenVictoryCountReach3(){
    units.put(new Position(3,1), new UnitImpl(GameConstants.SETTLER,Player.BLUE));
    units.put(new Position(2,1), new UnitImpl(GameConstants.SETTLER,Player.BLUE));
    game.getUnitAt(new Position(2,0)).changeAttackStrength(20);
    game.moveUnit(new Position(2,0), new Position(2,1));
    endOfRound();
    game.getUnitAt(new Position(2,1)).changeAttackStrength(20);
    game.moveUnit(new Position(2,1),new Position(3,1));
    endOfRound();
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
    endOfRound();
    game.getUnitAt(new Position(3,1)).changeAttackStrength(20);
    game.moveUnit(new Position(3,1),new Position(2,1));
    endOfRound();
    game.getUnitAt(new Position(2,1)).changeAttackStrength(20);
    game.moveUnit(new Position(2,1), new Position(2,0));
    assertThat(game.getWinner(), is(Player.BLUE));
  }

  @Test
  public void redArcherInCityShouldHave6AttackStrength(){
    Position attackingUnitInCity = new Position(1,1);
    Position defendingUnit = new Position(2,1);
    removeNeighbours(attackingUnitInCity);
    units.put(defendingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    endOfRound();
    endOfRound();
    game.getBattleStrategy().battleTest(game,attackingUnitInCity,defendingUnit);
    assertThat(game.getBattleStrategy().getAttackingUnitStrength(), is(6));
  }

  @Test
  public void redArcherInCityShouldHave9DefenseStrength(){
    Position defendingUnitInCity  = new Position(1,1);
    Position attackingUnit = new Position(2,1);
    removeNeighbours(defendingUnitInCity);
    units.put(attackingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    endOfRound();
    endOfRound();
    game.getBattleStrategy().battleTest(game,attackingUnit,defendingUnitInCity);
    assertThat(game.getBattleStrategy().getDefenseUnitStrength(), is(9));
  }

  @Test
  public void redLegionInCityShouldHave12AttackStrength(){
    Position attackingUnitInCity = new Position(1,1);
    Position defendingUnit = new Position(2,1);
    removeNeighbours(attackingUnitInCity);
    game.changeProductionInCityAt(attackingUnitInCity, GameConstants.LEGION);
    units.put(defendingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    endOfRound();
    endOfRound();
    endOfRound();
    game.getBattleStrategy().battleTest(game,attackingUnitInCity,defendingUnit);
    assertThat(game.getBattleStrategy().getAttackingUnitStrength(), is(12));
  }

  @Test
  public void redLegionInCityShouldHave6DefenseStrength(){
    Position defendingUnitInCity  = new Position(1,1);
    Position attackingUnit = new Position(2,1);
    removeNeighbours(defendingUnitInCity);
    game.changeProductionInCityAt(defendingUnitInCity, GameConstants.LEGION);
    units.put(attackingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    endOfRound();
    endOfRound();
    endOfRound();
    game.getBattleStrategy().battleTest(game,attackingUnit,defendingUnitInCity);
    assertThat(game.getBattleStrategy().getDefenseUnitStrength(), is(6));
  }

  @Test
  public void redArcherOnHillShouldHave4Attack(){
    Position attackingUnitOnHill = new Position(0,1);
    Position defendingUnit = new Position(0,2);
    units.put(attackingUnitOnHill, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(defendingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    game.getBattleStrategy().battleTest(game, attackingUnitOnHill, defendingUnit);
    assertThat(game.getBattleStrategy().getAttackingUnitStrength(), is(4));
  }

  @Test
  public void redArcherOnHillShouldHave6Defense(){
    Position defendingUnitOnHill = new Position(0,1);
    Position attackingUnit = new Position(0,2);
    units.put(defendingUnitOnHill, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(attackingUnit, new UnitImpl(GameConstants.LEGION,Player.BLUE));
    game.getBattleStrategy().battleTest(game, attackingUnit, defendingUnitOnHill);
    assertThat(game.getBattleStrategy().getDefenseUnitStrength(), is(6));
  }

  @Test
  public void redArcherSorroundedBy2FriendlyUnitsShouldHave4Attack() {
    Position archerPositionMain = new Position(8,8);
    Position archerPosition2 = new Position(8,7);
    Position archerPosition3 = new Position(7,8);
    Position defendingPosition = new Position(8,9);
    units.put(archerPositionMain, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(defendingPosition, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    game.getBattleStrategy().battleTest(game, archerPositionMain, defendingPosition);
    assertThat(game.getBattleStrategy().getAttackingUnitStrength(), is(4));
  }

  @Test
  public void redArcherSorroundedBy2FriendlyUnitsShouldHave5Defense() {
    Position archerPositionMain = new Position(8,8);
    Position archerPosition2 = new Position(8,7);
    Position archerPosition3 = new Position(7,8);
    Position attackingPosition = new Position(8,9);
    units.put(archerPositionMain, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(attackingPosition, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    game.getBattleStrategy().battleTest(game, attackingPosition, archerPositionMain);
    assertThat(game.getBattleStrategy().getDefenseUnitStrength(), is(5));
  }

  @Test
  public void redArcherInCityWith2FriendsShouldHave12Attack(){
    Position archerPositionMain = new Position(8,8);
    Position archerPosition2 = new Position(8,7);
    Position archerPosition3 = new Position(7,8);
    Position defendingPosition = new Position(8,9);
    game.createCityAtPosition(archerPositionMain);

    units.put(archerPositionMain, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(defendingPosition, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    game.getBattleStrategy().battleTest(game, archerPositionMain, defendingPosition);
    assertThat(game.getBattleStrategy().getAttackingUnitStrength(), is(12));
  }

  @Test
  public void redArcherOnHillWith2FriendsShouldHave8Attack(){
    Position archerPositionMain = new Position(8,8);
    Position archerPosition2 = new Position(8,7);
    Position archerPosition3 = new Position(7,8);
    Position defendingPosition = new Position(8,9);
    game.createTileAtPosition(archerPositionMain, new TileImpl(GameConstants.HILLS));

    units.put(archerPositionMain, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(defendingPosition, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    game.getBattleStrategy().battleTest(game, archerPositionMain, defendingPosition);
    assertThat(game.getBattleStrategy().getAttackingUnitStrength(), is(8));
  }


  @Test
  public void redArcherOnHillInCityWith2FriendsShouldHave24Attack(){
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
    game.getBattleStrategy().battleTest(game, archerPositionMain, defendingPosition);
    assertThat(game.getBattleStrategy().getAttackingUnitStrength(), is(24));
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
    assertTrue(game.getBattleStrategy().battle(game, redArcher, blueArcher));
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
    assertTrue(game.getBattleStrategy().battle(game, blueArcher, redArcher));
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
    assertFalse(game.getBattleStrategy().battle(game, blueArcher, redArcher));
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

