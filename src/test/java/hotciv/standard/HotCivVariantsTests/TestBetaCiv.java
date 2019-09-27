package hotciv.standard.HotCivVariantsTests;

import hotciv.framework.*;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.BetaCivFactory;
import hotciv.standard.strategies.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for BetaCiv test cases

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
    game = new GameImpl(new BetaCivFactory());
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
    //Unit has been produced in city
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



