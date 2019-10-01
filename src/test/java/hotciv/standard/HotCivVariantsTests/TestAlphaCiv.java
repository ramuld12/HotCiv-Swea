package hotciv.standard.HotCivVariantsTests;


import hotciv.framework.*;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.AlphaCivFactory;
import hotciv.standard.TestUtility;
import hotciv.standard.UnitImpl;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

// Skeleton class for AlphaCiv test cases

public class TestAlphaCiv {
  private GameImpl game;
  private TestUtility util;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new AlphaCivFactory());
    assertThat(game, is(notNullValue()));
    util = new TestUtility(game);
  }

  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void redCityShouldBeAt1_1() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(1,1);
    assertThat(game.getCityAt(p).getOwner(), is(Player.RED));
  }

  @Test
  public void blueCityShouldBeAt4_1() {
    Position p = new Position(4,1);
    assertThat(game.getCityAt(p).getOwner(), is(Player.BLUE));
  }


  @Test
  public void blueShouldBeAfterRed() {
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void redShouldBeginEachRound(){
    util.endOfRound();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void gameShouldBeginInYear4000BC() {
    assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void gameShouldAdvances100YearsPrRound() {
    util.endOfRound();
    util.endOfRound();
    assertThat(game.getAge(), is(-3800));
  }

  @Test
  public void redShouldWinInYear3000BC() {
    for (int i = 0; i<10; i++) { util.endOfRound(); }
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void winnerShouldBeNullBetween4000BCAnd3100BC() {
    for (int i = 0; i<9; i++) {
      util.endOfRound();
      assertNull(game.getWinner());
    }
  }

  @Test
  public void shouldBeOceanAtTile1_0() {
    Position p = new Position(1,0);
    assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.OCEANS));
  }

  @Test
  public void shouldBeHillsAtTile0_1() {
    Position p = new Position(0,1);
    assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.HILLS));
  }

  @Test
  public void shouldBeMountainsAtTile2_2() {
    Position p = new Position(2,2);
    assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.MOUNTAINS));
  }

  @Test
  public void shouldBePlainsByDefault() {
    assertThat(game.getTileAt(new Position(14,10)).getTypeString(), is(GameConstants.PLAINS));
    assertThat(game.getTileAt(new Position(13,10)).getTypeString(), is(GameConstants.PLAINS));
    assertThat(game.getTileAt(new Position(14,11)).getTypeString(), is(GameConstants.PLAINS));
    assertThat(game.getTileAt(new Position(14,12)).getTypeString(), is(GameConstants.PLAINS));
    assertThat(game.getTileAt(new Position(2,3)).getTypeString(), is(GameConstants.PLAINS));
  }

  @Test
  public void shouldAdd6ProductionEachRound() {
    util.endOfRound();
    assertThat(game.getCityAt(new Position(1,1)).getTreasury(), is(6));
  }

  /**
   * Tests for the Unit Class
   */
  @Test
  public void redShouldHaveAnArcherAtTile2_0() {
    Position p = new Position(2,0);
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(p).getOwner(), is(Player.RED));
  }

  @Test
  public void redShouldHaveALegionAtTile3_2() {
    Position p = new Position(3,2);
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.LEGION));
    assertThat(game.getUnitAt(p).getOwner(), is(Player.BLUE));
  }

  @Test
  public void redShouldHaveASettlerAtTile4_3() {
    Position p = new Position(4,3);
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.SETTLER));
    assertThat(game.getUnitAt(p).getOwner(), is(Player.RED));
  }

  @Test
  public void itShouldNotBeAllowedToMoveToMountainsOrOcean() {
    Position p1 = new Position(2,0); Position p2 = new Position(1,0);
    assertThat(game.moveUnit(p1, p2), is(false));
  }

  @Test
  public void archersShouldOnlyMove1Tile() {
    Position p1 = new Position(2,0); Position p2 = new Position(3,0);
    game.movingTheUnit(p1, p2);
    assertThat(game.getUnitAt(p2).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void archersShouldNotBeAbleToMove2Tiles() {
    Position p1 = new Position(2,0); Position p2 = new Position(4,0);
    game.moveUnit(p1, p2);
    assertFalse(game.moveUnit(p1, p2));
  }

  @Test
  public void movingAUnitShouldHaveAUnitAtTheFromPosition() {
    Position p1 = new Position(2,0); Position p2 = new Position(2,1);
    assertThat(game.moveUnit(p2,p1), is(false));
    assertThat(game.moveUnit(p1, p2), is(true));
  }

  @Test
  public void movingAUnitShouldRemoveTheUnitAndCreateANew() {
    Position p1 = new Position(2,0); Position p2 = new Position(2,1);
    game.movingTheUnit(p1,p2);
    assertNull(game.getUnitAt(p1));
    assertThat(game.getUnitAt(p2).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void aMovedUnitShouldHaveTheSameOwner() {
    Position p1 = new Position(3,2); Position p2 = new Position(2,1);
    game.endOfTurn();
    game.movingTheUnit(p1,p2);
    assertThat(game.getUnitAt(p2).getOwner(), is(Player.BLUE));
  }

  @Test
  public void movingAUnitSuccesfullyShouldReturnTrue() {
    Position p1 = new Position(2,0); Position p2 = new Position(2,1);
    assertTrue(game.moveUnit(p1,p2));
  }

  @Test
  public void redShouldNotBeAbleToMoveBlueUnits() {
    Position p1 = new Position(3,2); Position p2 = new Position(2,1);
    assertThat(game.moveUnit(p1,p2), is(false));
  }

  @Test
  public void aUnitShouldNotBeAbleToMoveMoreThanItsMoveCounter() {
    Position p1 = new Position(3,2); Position p2 = new Position(5,2);
    game.endOfTurn();//Blue should be in turn in this test
    assertThat(game.moveUnit(p1,p2), is(false));
  }

  @Test
  public void attackingUnitShouldAlwaysWinTest1() {
    Position p1 = new Position(3,2); Position p2 = new Position(4,3);
    game.moveUnit(p2,p1);
    assertThat(game.getUnitAt(p1).getTypeString(), is(GameConstants.SETTLER));
  }

  @Test
  public void attackingUnitShouldAlwaysWinTest2() {
    Position p1 = new Position(3,2); Position p2 = new Position(4,3);
    game.endOfTurn();
    game.moveUnit(p1,p2);
    assertThat(game.getUnitAt(p2).getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void aUnitShouldHave0MoveCounterAfterMoving() {
    Position p2 = new Position(4,3); Position p1 = new Position(3,2);
    game.moveUnit(p2, p1);
    assertThat(game.getUnitAt(p1).getMoveCount(), is(0));
  }

  @Test
  public void theMoveCounterOfUnitsShouldBeRestoredAfterEndOfRound() {
    //Move red's units
    Position p1 = new Position(2,0); Position p2 = new Position(3,0);
    game.moveUnit(p1, p2);
    Position p3 = new Position(4,3); Position p4 = new Position(4,4);
    game.moveUnit(p3, p4);
    game.endOfTurn();

    //Move blue's unit
    Position p5 = new Position(3,2); Position p6 = new Position(3,3);
    game.moveUnit(p5,p6);
    game.endOfTurn();

    //Make sure they have 1 movecounter after finished round
    assertThat(game.getUnitAt(p2).getMoveCount(), is(1));
    assertThat(game.getUnitAt(p4).getMoveCount(), is(1));
    assertThat(game.getUnitAt(p6).getMoveCount(), is(1));
  }

  @Test
  public void shouldChangeProductionUnit(){
    Position p = new Position(1,1);
    game.changeProductionInCityAt(p,GameConstants.ARCHER);
    assertThat(game.getCityAt(p).getProduction(), is(GameConstants.ARCHER));

    Position m = new Position(4,1);
    game.changeProductionInCityAt(m,GameConstants.SETTLER);
    assertThat(game.getCityAt(m).getProduction(), is(GameConstants.SETTLER));
  }

  @Test
  public void shouldCreateUnitInVacantRedCity() {
    Position p = new Position(1,1);
    assertNull(game.getUnitAt(p));
    util.endOfRound();
    util.endOfRound();
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void shouldReduceTreasuryWhenProducingUnit() {
    Position p = new Position(1,1);
    util.endOfRound();
    util.endOfRound();
    assertThat(game.getCityAt(p).getTreasury(), is(2));
  }

  @Test
  public void shouldCreateUnitInVacantBlueCity() {
    Position p = new Position(4,1);
    game.changeProductionInCityAt(p,GameConstants.LEGION);
    assertNull(game.getUnitAt(p));
    util.endOfRound();
    util.endOfRound();
    util.endOfRound();
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void shouldCreateUnitNorthOfBlueCity() {
    Position p = new Position(3,1);
    assertNull(game.getUnitAt(p));
    util.endOfRound();
    util.endOfRound();
    //Unit created in blue city at (4,1)

    util.endOfRound();
    util.endOfRound();
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void shouldNotAllowMovingFromMinus1_Minus0 () {
    Position p1 = new Position (0,1);
    Position p2 = new Position (-1,0);
    assertFalse(game.moveUnit(p2, p1));
  }

  @Test
  public void shouldNotAllowMovingToMinus1_Minus0 () {
    Position p1 = new Position (0,1);
    Position p2 = new Position (-1,0);
    assertFalse(game.moveUnit(p2, p1));
  }

  @Test
  public void shouldNotAllowBlueLegionToMoveToTileWithBlueUnit() {
    Position p1 = new Position(4,1);
    Position p2 = new Position(3,2);

    game.changeProductionInCityAt(p1,GameConstants.LEGION);
    util.endOfRound();
    util.endOfRound();
    util.endOfRound();
    assertFalse(game.moveUnit(p1, p2));
  }

  @Test
  public void shouldCreateCityAtPosition () {
    Position p = new Position(4,5);
    game.createCityAtPosition(p);
    assertNotNull(game.getCityAt(p));
  }

  @Test
  public void shouldRemoveUnitAt4_3() {
    Position unitPosition = new Position(4,3);
    game.removeUnitFromUnitsMapAtPosition(unitPosition);
    assertNull(game.getUnitAt(unitPosition));
  }

  @Test
  public void shouldReturnTrueIfPlayerOwnsAllCities(){
    Position blueCity = new Position(4,1);
    Position unitPosition = new Position(4,3);
    game.moveUnit(unitPosition, new Position(4,2));
    util.endOfRound();
    game.moveUnit(new Position(4,2),blueCity);
    assertTrue(game.doesPlayerInTurnOwnAllCities());

  }

  @Test
  public void shouldReturnFalseIfPlayerDoesNotOWnAllCities(){
    assertFalse(game.doesPlayerInTurnOwnAllCities());
  }

  @Test
  public void shouldReturnFirstVacantPositionAroundCenterPosition(){
    Position centerPosition = new Position(1,1);
    Position vacantPosition = game.findFirstVacantNeighbourPosition(centerPosition);
    assertThat(vacantPosition, is(new Position(0,1)));
    Position centerPosition2 = new Position(3,0);
    Position vacantPosition2 = game.findFirstVacantNeighbourPosition(centerPosition2);
    assertThat(vacantPosition2, is(new Position(2,1)));
  }

  @Test
  public void shouldFind2FriendlyUnits(){
    HashMap<Position, UnitImpl> units = game.getUnits();
    Position archerPositionMain = new Position(8,8);
    Position archerPosition2 = new Position(8,7);
    Position archerPosition3 = new Position(7,8);
    Position defendingPosition = new Position(8,9);
    game.createCityAtPosition(archerPositionMain);

    units.put(archerPositionMain, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition2, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(archerPosition3, new UnitImpl(GameConstants.ARCHER,Player.RED));
    units.put(defendingPosition, new UnitImpl(GameConstants.ARCHER,Player.BLUE));
    int numberOfFriends = game.findNumberOfFriendlyNeighbourUnits(archerPositionMain);
    assertTrue(numberOfFriends == 2);
  }

}



