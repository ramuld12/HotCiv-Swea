package hotciv.standard.HotCivVariantsTests;

import hotciv.framework.*;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.DeltaCivFactory;
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
    game = new GameImpl(new DeltaCivFactory());
    assertThat(game, is(notNullValue()));
  }

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


}



