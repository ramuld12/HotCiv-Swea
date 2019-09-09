package hotciv.standard;

import hotciv.framework.*;

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
  private Game game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
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
  public void shouldAdd6ProductionprRound() {
    endOfRound();
    assertThat(game.getCityAt(new Position(1,1)).getTreasury(), is(6));
    endOfRound();
    assertThat(game.getCityAt(new Position(1,1)).getTreasury(), is(12));
  }

}


