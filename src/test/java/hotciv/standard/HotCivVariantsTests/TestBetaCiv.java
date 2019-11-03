package hotciv.standard.HotCivVariantsTests;

import hotciv.framework.*;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.BetaCivFactory;
import hotciv.standard.TestStubs.GameObserverImplTest;
import hotciv.standard.TestUtility;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class TestBetaCiv {
  private GameImpl game;
  private TestUtility util;

  /** Fixture for betaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new BetaCivFactory(), new GameObserverImplTest());
    assertThat(game, is(notNullValue()));
    util = new TestUtility(game);
  }

  @Test
  public void gameShouldAdvance100YearsPrRoundBetween4000BCAnd100BC() {
    util.endOfRound();
    util.endOfRound();
    assertThat(game.getAge(), is(-3800));
  }

  @Test
  public void gameShouldAdvanceTo1BCAtYear100BC() {
    game.setGameAge(-100);
    util.endOfRound();
    assertThat(game.getAge(), is(-1));
  }

  @Test
  public void gameShouldAdvanceTo1ADAtYear1BC() {
    game.setGameAge(-1);
    util.endOfRound();
    assertThat(game.getAge(), is(1));
  }

  @Test
  public void gameShouldAdvanceTo50AtYear1() {
    game.setGameAge(1);
    util.endOfRound();
    assertThat(game.getAge(), is(50));
  }

  @Test
  public void gameShouldAdvance50PrRoundAtYear50ToYear1750() {
    game.setGameAge(50);
    util.endOfRound();
    assertThat(game.getAge(), is(100));
  }

  @Test
  public void gameShouldAdvance25PrRoundAtYear1750ToYear1900() {
    game.setGameAge(1750);
    util.endOfRound();
    assertThat(game.getAge(), is(1775));
  }

  @Test
  public void gameShouldAdvance5PrRoundAtYear1900ToYear1970() {
    game.setGameAge(1905);
    util.endOfRound();
    assertThat(game.getAge(), is(1910));
  }

  @Test
  public void gameShouldAdvance1PrRoundAtYear1970AndOn() {
    game.setGameAge(1970);
    util.endOfRound();
    util.endOfRound();
    assertThat(game.getAge(), is(1972));
  }

  @Test
  public void redShouldConquerVacantBlueCityUponEntering(){
    Position p1 = new Position(4,3);
    Position p2 = new Position(4,2);
    Position p3 = new Position(4,1);

    game.moveUnit(p1,p2);
    util.endOfRound();
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
    util.endOfRound();
    util.endOfRound();
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
    util.endOfRound();
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
    util.endOfRound();
    game.moveUnit(p2,p3);
    assertThat(game.getWinner(), is(Player.BLUE));
  }

}



