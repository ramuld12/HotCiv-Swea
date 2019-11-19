package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.HotCivFactory.AlphaCivFactory;
import hotciv.standard.TestStubs.GameObserverImplTest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    game = new GameImpl(new AlphaCivFactory());
    assertThat(game, is(notNullValue()));
    util = new TestUtility(game);
    cities = game.getCities();
    game.addObserver(new GameObserverImplTest());
    observer = (GameObserverImplTest)game.getObservers().get(game.getObservers().size()-1);
  }

  @Test
  public void shouldHaveBlueInTurnAfterEndOfTurn() {
    game.endOfTurn();
    assertThat(observer.getEndOfTurnPlayers().get(0), is(Player.BLUE));
  }

  @Test
  public void shouldHaveRedInTurnAfterTwoEndOfTurn() {
    game.endOfTurn();
    game.endOfTurn();
    assertThat(observer.getEndOfTurnPlayers().get(1), is(Player.RED));
  }

  @Test
  public void shouldCallEndOfTurnWithGameAgeMinus4000() {
    game.endOfTurn();
    assertThat(observer.getEndOfTurnAges().get(0), is(-4000));
  }

  @Test
  public void worldChangeShouldCallWhenCreatingCity() {
    Position cityPos = new Position(2,1);
    game.createCityAtPosition(cityPos);
    assertThat(observer.getWorldChanges().get(0), is(cityPos));
  }

  @Test
  public void worldChangeShouldCallWhenCreatingUnit() {
    Position unitPos = new Position(2,1);
    game.createUnitAtPosition(unitPos, GameConstants.LEGION, game.getPlayerInTurn());
    assertThat(observer.getWorldChanges().get(0), is(unitPos));
  }

  @Test
  public void worldChangeShouldCallWhenCreatingTile() {
    Position tilePos = new Position(2,1);
    game.createTileAtPosition(tilePos, new TileImpl(GameConstants.HILLS));
    assertThat(observer.getWorldChanges().get(0), is(tilePos));
  }

  @Test
  public void shouldCallWhenProducingUnit() {
    Position cityPos = new Position(1,1);
    cities.get(cityPos).setTreas(20);
    game.produceUnitInCityAt(cityPos, cities.get(cityPos));
    assertThat(observer.getWorldChanges().get(0), is(cityPos));
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
  public void shouldChangeWorldWhenPerformingUnitAction() {
    Position settlerPos = new Position(4,3);
    game.performUnitActionAt(settlerPos);
    assertThat(observer.getWorldChanges().get(0), is(settlerPos));
  }

  @Test
  public void worldChangeShouldCallWhenCreatingUnitAtNeighbourPosition() {
    Position centerPos = new Position(2,1);
    game.createUnitAtNeighbourPosition(centerPos, GameConstants.LEGION, game.getPlayerInTurn());
    assertThat(observer.getWorldChanges().get(0), is(new Position(1,1)));
  }
  @Test
  public void worldChangeShouldCallWhenRemovingCity() {
    Position cityPos = new Position(1,1);
    game.removeCityFromCitiesMapAtPosition(cityPos);
    assertThat(observer.getWorldChanges().get(0), is(cityPos));
  }

  @Test
  public void worldChangeShouldCallWhenRemovingUnit() {
    Position unitPos = new Position(2,0);
    game.removeUnitFromUnitsMapAtPosition(unitPos);
    assertThat(observer.getWorldChanges().get(0), is(unitPos));
  }

  @Test
  public void worldChangeShouldCallWhenRemovingTile() {
    Position tilePos = new Position(2,1);
    game.removeTileFromWorldMapAtPosition(tilePos);
    assertThat(observer.getWorldChanges().get(0), is(tilePos));
  }


}
