package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.standard.Broker.Invokers.CityInvoker;
import hotciv.standard.Broker.LocalMethodClientRequestHandler;
import hotciv.standard.Broker.Proxies.CityProxy;
import hotciv.standard.Broker.BrokerStubs.StubCityBroker;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestCityBroker {
  private City city;

  @Before
  public void setUp() {
    City servant = new StubCityBroker();

    Invoker invoker = new CityInvoker(servant);

    ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

    Requestor requestor = new StandardJSONRequestor(crh);

    city = new CityProxy("", requestor);
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
