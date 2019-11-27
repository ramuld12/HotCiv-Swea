package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerStubs.StubGame3Broker;
import hotciv.standard.Broker.Invokers.CityInvoker;
import hotciv.standard.Broker.Invokers.RootInvoker;
import hotciv.standard.Broker.LocalMethodClientRequestHandler;
import hotciv.standard.Broker.NameServiceImpl;
import hotciv.standard.Broker.NullObserver;
import hotciv.standard.Broker.Proxies.CityProxy;
import hotciv.standard.Broker.BrokerStubs.StubCityBroker;
import hotciv.standard.Broker.Proxies.GameProxy;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestCityBroker {
  private City city;
  private Game game;

  @Before
  public void setUp() {
    Game servant = new StubGame3Broker();
    GameObserver nullObserver = new NullObserver();
    servant.addObserver(nullObserver);

    Invoker invoker = new RootInvoker(servant, nullObserver);

    ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

    Requestor requestor = new StandardJSONRequestor(crh);

    game = new GameProxy("", requestor);
    game.addObserver(nullObserver);
    this.city = game.getCityAt(new Position(1,1));
  }

  @Test
  public void shouldReturnPlayerRed(){
    assertThat(city.getOwner(), is(Player.RED));
  }

  @Test
  public void shouldReturn42(){
    assertThat(city.getSize(), is(42));
  }

  @Test
  public void shouldReturn100() {
    assertThat(city.getTreasury(),is(100));
  }

  @Test
  public void shouldReturnArcherString(){
    assertThat(city.getProduction(), is(GameConstants.ARCHER));
  }
}
