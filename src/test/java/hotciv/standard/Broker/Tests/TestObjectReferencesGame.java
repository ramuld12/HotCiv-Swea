package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.Invokers.GameInvoker;
import hotciv.standard.Broker.Invokers.RootInvoker;
import hotciv.standard.Broker.LocalMethodClientRequestHandler;
import hotciv.standard.Broker.NullObserver;
import hotciv.standard.Broker.Proxies.GameProxy;
import hotciv.standard.Broker.SpyRequester;
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
  }

  @Test
  public void shouldGetUnitObjectAt2_2() {
    Position unitPos = new Position(2,2);
    assertNotNull(game.getUnitAt(unitPos));



    //assertThat(game.getCityAt(cityPos).getOwner(), is(Player.RED));
  }

  @Test
  public void shouldGetTileObjectAt2_2() {
    Position tilePos = new Position(2,2);
    assertNotNull(game.getTileAt(tilePos));



    //assertThat(game.getCityAt(cityPos).getOwner(), is(Player.RED));
  }

}
