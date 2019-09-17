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
public class TestBetaCiv {
  private GameImpl game;

  /** Fixture for betaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new BetaCivAgingStrategy(), new BetaCivWinningStrategy(), new AlphaCivUnitActionStrategy(), new AlphaCivWorldLayoutStrategy());
    assertThat(game, is(notNullValue()));
  }

  /**
   * Method for testing end of round triggers
   */
  public void endOfRound() {
    game.endOfTurn();
    game.endOfTurn();
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
    endOfRound();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void gameShouldBeginInYear4000BC() {
    assertThat(game.getAge(), is(-4000));
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
    endOfRound();
    endOfRound();
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void shouldNotAllowMovingFromMinus1_Minus0 () {
    Position p1 = new Position (0,1);
    Position p2 = new Position (-1,0);
    assertFalse(game.moveUnit(p1, p2));
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
  public void gameShouldAdvance100YearsPrRoundBetween4000BCAnd100BC() {
    endOfRound();
    endOfRound();
    assertThat(game.getAge(), is(-3800));
  }

  @Test
  public void gameShouldAdvanceTo1BCAtYear100BC() {
    game.setGameAge(-100);
    endOfRound();
    assertThat(game.getAge(), is(-1));
  }

  @Test
  public void gameShouldAdvanceTo1ADAtYear1BC() {
    game.setGameAge(-1);
    endOfRound();
    assertThat(game.getAge(), is(1));
  }

  @Test
  public void gameShouldAdvanceTo50AtYear1() {
    game.setGameAge(1);
    endOfRound();
    assertThat(game.getAge(), is(50));
  }

  @Test
  public void gameShouldAdvance50PrRoundAtYear50ToYear1750() {
    game.setGameAge(50);
    endOfRound();
    assertThat(game.getAge(), is(100));
  }

  @Test
  public void gameShouldAdvance25PrRoundAtYear1750ToYear1900() {
    game.setGameAge(1750);
    endOfRound();
    assertThat(game.getAge(), is(1775));
  }

  @Test
  public void gameShouldAdvance5PrRoundAtYear1900ToYear1970() {
    game.setGameAge(1905);
    endOfRound();
    assertThat(game.getAge(), is(1910));
  }

  @Test
  public void gameShouldAdvance1PrRoundAtYear1970AndOn() {
    game.setGameAge(1970);
    endOfRound();
    endOfRound();
    assertThat(game.getAge(), is(1972));
  }

  @Test
  public void redShouldConquerVacantBlueCityUponEntering(){
    Position p1 = new Position(4,3);
    Position p2 = new Position(4,2);
    Position p3 = new Position(4,1);

    game.moveUnit(p1,p2);
    endOfRound();
    assertNull(game.getUnitAt(p3));
    game.moveUnit(p2,p3);
    assertThat(game.getCityAt(p3).getOwner(), is(Player.RED));
  }

  @Test
  public void redShouldConquerOccupiedBlueCityUponEntering(){
    Position p1 = new Position(4,3);
    Position p2 = new Position(4,2);
    Position p3 = new Position(4,1);

    game.moveUnit(p1,p2);
    endOfRound();
    endOfRound();
    assertNotNull(game.getUnitAt(p3));
    game.moveUnit(p2,p3);
    assertThat(game.getCityAt(p3).getOwner(), is(Player.RED));
  }

  @Test
  public void redShouldWinGameWhenConqueringAllBlueCities() {
    Position p1 = new Position(4,3);
    Position p2 = new Position(4,2);
    Position p3 = new Position(4,1);

    game.moveUnit(p1,p2);
    endOfRound();
    game.moveUnit(p2,p3);
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void blueShouldWinGameWhenConqueringAllRedCities() {
    Position p1 = new Position(3,2);
    Position p2 = new Position(2,1);
    Position p3 = new Position(1,1);
    game.endOfTurn();
    game.moveUnit(p1,p2);
    endOfRound();
    game.moveUnit(p2,p3);
    assertThat(game.getWinner(), is(Player.BLUE));
  }

}



