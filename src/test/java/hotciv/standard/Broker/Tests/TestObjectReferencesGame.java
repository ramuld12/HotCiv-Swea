package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import hotciv.framework.*;
import hotciv.standard.Broker.*;
import hotciv.standard.Broker.BrokerStubs.StubCityBroker;
import hotciv.standard.Broker.Invokers.GameInvoker;
import hotciv.standard.Broker.Invokers.RootInvoker;
import hotciv.standard.Broker.Proxies.CityProxy;
import hotciv.standard.Broker.Proxies.GameProxy;
import hotciv.standard.Broker.BrokerStubs.StubGame3Broker;
import org.junit.*;

import java.lang.reflect.Type;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestObjectReferencesGame {

  private Game game;
  private SpyRequester requestor;

  @Before
  public void setUp() {
    Game servant = new StubGame3Broker();
    GameObserver nullObserver = new NullObserver();
    servant.addObserver(nullObserver);

    Invoker invoker = new RootInvoker(servant);

    ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

    SpyRequester requestor = new SpyRequester(crh);

    game = new GameProxy("3", requestor);
    game.addObserver(nullObserver);
    this.requestor = requestor;
  }

  @Test
  public void shouldGetCityObjectAt2_2() {
    Position cityPos = new Position(2,2);
    City city = game.getCityAt(cityPos);
    assertNotNull(city);
    assertThat(city.getOwner(), is(Player.RED));
    assertThat(city.getSize(), is(42));
    assertEquals(city.getClass(), CityProxy.class);
  }

  @Test
  public void shouldGetUnitObjectAt2_2() {
    Position unitPos = new Position(2,2);
    assertNotNull(game.getUnitAt(unitPos));
    assertThat(game.getUnitAt(unitPos).getOwner(), is(Player.RED));
    assertThat(game.getUnitAt(unitPos).getAttackingStrength(), is(10));
    assertThat(game.getUnitAt(unitPos).getMoveCount(), is(42));
  }

  @Test
  public void shouldGetTileObjectAt2_2() {
    Position tilePos = new Position(2,2);
    assertNotNull(game.getTileAt(tilePos));
    assertThat(game.getTileAt(tilePos).getTypeString(), is(GameConstants.PLAINS));
  }

}
