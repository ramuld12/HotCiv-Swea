package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.HotCivFactory.AlphaCivFactory;
import hotciv.standard.TestStubs.GameObserverImplTest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestGameObserver {
  private GameImpl game;
  private GameObserverImplTest observer;
  private TestUtility util;
  private HashMap<Position, CityImpl> cities;

  @Before
  public void setUp() {
    observer = new GameObserverImplTest();
    game = new GameImpl(new AlphaCivFactory(), observer);
    assertThat(game, is(notNullValue()));
    util = new TestUtility(game);
    cities = game.getCities();
  }

  @Test
  public void shouldCallEndOfTurnWithRedPlayerInTurn() {
    game.endOfTurn();
    assertThat(observer.getEndOfTurnPlayers().get(0), is(Player.RED));
  }

  @Test
  public void shouldCallEndOfTurnWithGameAgeMinus4000() {
    game.endOfTurn();
    assertThat(observer.getEndOfTurnAges().get(0), is(-4000));
  }

  @Test
  public void shouldCallMoveUnitAtFrom2_0(){
    Position from = new Position(2,0);
    Position to = new Position(2,1);
    game.moveUnit(from,to);
    assertThat(observer.getWorldChanges().get(0),is(from));
  }

  @Test
  public void shouldCallMoveUnitAtTo2_1(){
    Position from = new Position(2,0);
    Position to = new Position(2,1);
    game.moveUnit(from,to);
    assertThat(observer.getWorldChanges().get(1),is(to));
  }

  @Test
  public void shouldCallWhenChangingProduction() {
    Position cityPos = new Position(1,1);
    game.changeProductionInCityAt(cityPos, GameConstants.LEGION);
    assertThat(observer.getWorldChanges().get(0), is(cityPos));
  }

  @Test
  public void worldChangeShouldCallWhenCreatingCity() {
    Position cityPos = new Position(2,1);
    game.createCityAtPosition(cityPos);
    assertThat(observer.getWorldChanges().get(0), is(cityPos));
  }

  @Test
  public void shouldCallWhenProducingUnit() {
    Position cityPos = new Position(1,1);
    cities.get(cityPos).setTreas(20);
    game.produceUnitInCityAt(cityPos, cities.get(cityPos));
    assertThat(observer.getWorldChanges().get(0), is(cityPos));
  }
}
