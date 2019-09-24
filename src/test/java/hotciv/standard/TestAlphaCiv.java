package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.strategies.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public class TestAlphaCiv {
  private GameImpl game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new AlphaCivAgingStrategy(), new AlphaCivWinningStrategy(), new AlphaCivUnitActionStrategy(), new AlphaCivWorldLayoutStrategy());
    assertThat(game, is(notNullValue()));
  }

  /**
   * Method for testing end of round triggers
   */
  public void endOfRound() {
    game.endOfTurn();
    game.endOfTurn();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
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
    endOfRound();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void gameShouldBeginInYear4000BC() {
    assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void gameShouldAdvances100YearsPrRound() {
    endOfRound();
    endOfRound();
    assertThat(game.getAge(), is(-3800));
  }

  @Test
  public void redShouldWinInYear3000BC() {
    for (int i = 0; i<10; i++) { endOfRound(); }
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void winnerShouldBeNullBetween4000BCAnd3100BC() {
    for (int i = 0; i<9; i++) {
      endOfRound();
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
    endOfRound();
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
    game.moveUnit(p1, p2);
    assertThat(game.getUnitAt(p2).getTypeString(), is(GameConstants.ARCHER));
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
    game.moveUnit(p1,p2);
    assertNull(game.getUnitAt(p1));
    assertThat(game.getUnitAt(p2).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void aMovedUnitShouldHaveTheSameOwner() {
    Position p1 = new Position(3,2); Position p2 = new Position(2,1);
    game.endOfTurn();
    game.moveUnit(p1,p2);
    assertThat(game.getUnitAt(p2).getOwner(), is(Player.BLUE));
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
    endOfRound();
    endOfRound();
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void shouldReduceTreasuryWhenProducingUnit() {
    Position p = new Position(1,1);
    endOfRound();
    endOfRound();
    assertThat(game.getCityAt(p).getTreasury(), is(2));
  }

  @Test
  public void shouldCreateUnitInVacantBlueCity() {
    Position p = new Position(4,1);
    game.changeProductionInCityAt(p,GameConstants.LEGION);
    assertNull(game.getUnitAt(p));
    endOfRound();
    endOfRound();
    endOfRound();
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void shouldCreateUnitNorthOfBlueCity() {
    Position p = new Position(3,1);
    assertNull(game.getUnitAt(p));
    endOfRound();
    endOfRound();
    //Unit created in blue city at (4,1)

    endOfRound();
    endOfRound();
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
    endOfRound();
    endOfRound();
    endOfRound();
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
    endOfRound();
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


}



