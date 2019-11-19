package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.Invokers.GameInvoker;
import hotciv.standard.Broker.LocalMethodClientRequestHandler;
import hotciv.standard.Broker.NullObserver;
import hotciv.standard.Broker.Proxies.GameProxy;
import hotciv.standard.Broker.SpyRequester;
import hotciv.standard.Broker.BrokerStubs.StubGame3Broker;
import org.junit.*;

import java.lang.reflect.Type;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestGameBroker {

  private Game game;
  private SpyRequester requestor;

  @Before
  public void setUp() {
    Game servant = new StubGame3Broker();
    GameObserver nullObserver = new NullObserver();
    servant.addObserver(nullObserver);

    Invoker invoker = new GameInvoker(servant);

    ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

    SpyRequester requestor = new SpyRequester(crh);

    game = new GameProxy(requestor);
    game.addObserver(nullObserver);
    this.requestor = requestor;
  }

  @Test
  public void gameAgeShouldBe42(){
    assertThat(game.getAge(), is(42));
  }

  @Test
  public void shouldBeRedAsWinner() {
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void shouldBeRedAsPlayerInTurn() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBeAllowedToMoveUnitFrom1_1To1_2() {
    assertTrue(game.moveUnit(new Position(1,1), new Position(1,2)));
  }

  @Test
  public void playerInTurnShouldAlternateWhenEndingTurn() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldRequestProductionInCity() {
    Position cityPos = new Position(1,2);
    game.changeProductionInCityAt(cityPos, GameConstants.ARCHER);

    //Variables for testing from the spy
    Position cityPosSpy = (Position)requestor.getLastArguments()[0];
    String newUnitType = (String)requestor.getLastArguments()[1];
    String operationName = requestor.getLastOperationName();
    String objectId = requestor.getLastObjectId();
    Type type = requestor.getLastType();
    System.out.println(type);

    //Assertions
    assertThat(cityPosSpy, is(cityPos));
    assertThat(newUnitType, is(GameConstants.ARCHER));
    assertThat(operationName, is(BrokerConstants.changeCityProduction));
    assertThat(objectId, is(BrokerConstants.gameId));
    assertThat(type.toString(), is("void"));
  }

  @Test
  public void shouldRequestPerformUnitAction() {
    game.performUnitActionAt(new Position(1,5));

    //Variables for testing from the spy
    Position actionPosition = (Position)requestor.getLastArguments()[0];
    String operationName = requestor.getLastOperationName();
    String objectId = requestor.getLastObjectId();
    Type type = requestor.getLastType();

    //Assertions
    assertThat(actionPosition, is(new Position(1,5)));
    assertThat(operationName, is(BrokerConstants.unitAction));
    assertThat(objectId, is(BrokerConstants.gameId));
    assertThat(type.toString(), is("void"));
  }
}
