package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.strategies.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for Deltaciv test cases

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
public class TestDeltaCiv {
  private Game game;

  /** Fixture for Deltaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new AlphaCivAgingStrategy(), new AlphaCivWinningStrategy(), new AlphaCivUnitActionStrategy(), new DeltaCivWorldLayoutStrategy());
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
  public void redCityShouldBeAt8_12() {
    assertThat(game, is(notNullValue()));
    Position p = new Position(8,12);
    assertThat(game.getCityAt(p).getOwner(), is(Player.RED));
  }

  @Test
  public void blueCityShouldBeAt4_5() {
    Position p = new Position(4,5);
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
    Position p = new Position(0,1);
    assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.OCEANS));
  }

  @Test
  public void shouldBeHillsAtTile1_3() {
    Position p = new Position(1,3);
    assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.HILLS));
  }

  @Test
  public void shouldBeMountainsAtTile5_0() {
    Position p = new Position(0,5);
    assertThat(game.getTileAt(p).getTypeString(), is(GameConstants.MOUNTAINS));
  }

  @Test
  public void shouldBePlainsByDefault() {
    assertThat(game.getTileAt(new Position(3,15)).getTypeString(), is(GameConstants.PLAINS));
    assertThat(game.getTileAt(new Position(10,4)).getTypeString(), is(GameConstants.PLAINS));
  }

  @Test
  public void shouldAdd6ProductionEachRound() {
    endOfRound();
    assertThat(game.getCityAt(new Position(8,12)).getTreasury(), is(6));
  }

  @Test
  public void shouldChangeProductionUnit(){
    Position p = new Position(8,12);
    game.changeProductionInCityAt(p,GameConstants.ARCHER);
    assertThat(game.getCityAt(p).getProduction(), is(GameConstants.ARCHER));

    Position m = new Position(4,5);
    game.changeProductionInCityAt(m,GameConstants.SETTLER);
    assertThat(game.getCityAt(m).getProduction(), is(GameConstants.SETTLER));
  }

  @Test
  public void shouldCreateUnitInVacantRedCity() {
    Position p = new Position(8,12);
    assertNull(game.getUnitAt(p));
    endOfRound();
    endOfRound();
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void shouldReduceTreasuryWhenProducingUnit() {
    Position p = new Position(8,12);
    endOfRound();
    endOfRound();
    assertThat(game.getCityAt(p).getTreasury(), is(2));
  }

  @Test
  public void shouldCreateUnitInVacantBlueCity() {
    Position p = new Position(4,5);
    game.changeProductionInCityAt(p,GameConstants.LEGION);
    assertNull(game.getUnitAt(p));
    endOfRound();
    endOfRound();
    endOfRound();
    assertThat(game.getUnitAt(p).getTypeString(), is(GameConstants.LEGION));
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

}



