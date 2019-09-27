package hotciv.standard.HotCivVariantsTests;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.GammaCivFactory;
import hotciv.standard.strategies.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for GammaCiv test cases

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
public class TestGammaCiv {
  private GameImpl game;

  /** Fixture for GammeCiv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new GammaCivFactory());
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
  public void settlerActionShouldResultInACity(){
    Position settlerPosition = new Position(4,3);
    game.performUnitActionAt(settlerPosition);
    assertNotNull(game.getCityAt(settlerPosition));
  }

  @Test
  public void redSettlerActionShouldResultInARedCity(){
    Position settlerPosition = new Position(4,3);
    game.performUnitActionAt(settlerPosition);
    assertThat(game.getCityAt(settlerPosition).getOwner(), is(Player.RED));
  }

  @Test
  public void ArcherShouldHave3DefenseAnd2Attack() {
    Position archerPosition = new Position(2,0);
    assertThat(game.getUnitAt(archerPosition).getDefensiveStrength(), is(3));
    assertThat(game.getUnitAt(archerPosition).getAttackingStrength(), is(2));
  }

  @Test
  public void LegionShouldHave2DefenseAnd4Attack() {
    Position legionPosition = new Position(3,2);
    assertThat(game.getUnitAt(legionPosition).getDefensiveStrength(), is(2));
    assertThat(game.getUnitAt(legionPosition).getAttackingStrength(), is(4));
  }

  @Test
  public void SettlerShouldHave3DefenseAnd0Attack() {
    Position settlerPosition = new Position(4,3);
    assertThat(game.getUnitAt(settlerPosition).getDefensiveStrength(), is(3));
    assertThat(game.getUnitAt(settlerPosition).getAttackingStrength(), is(0));
  }

  @Test
  public void archerActionShouldDoubleDefenceStrength(){
    Position archerPosition = new Position(2,0);
    assertThat(game.getUnitAt(archerPosition).getDefensiveStrength(), is(3));
    game.performUnitActionAt(archerPosition);
    assertThat(game.getUnitAt(archerPosition).getDefensiveStrength(), is(6));
  }

  @Test
  public void fortifiedArcherShouldNotBeAbleToMove(){
    Position archerPosition = new Position(2,0);
    game.performUnitActionAt(archerPosition);
    assertThat(game.moveUnit(archerPosition, new Position(2,1)), is(false));
  }

  @Test
  public void shouldRemoveFortificationFromArcher() {
    Position archerPosition = new Position(2,0);
    game.performUnitActionAt(archerPosition);
    game.performUnitActionAt(archerPosition);
    assertThat(game.getUnitAt(archerPosition).getDefensiveStrength(), is(3));
    assertTrue(game.getUnitAt(archerPosition).isMoveable());
  }

}




