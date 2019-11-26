package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.standard.Broker.Invokers.TileInvoker;
import hotciv.standard.Broker.LocalMethodClientRequestHandler;
import hotciv.standard.Broker.NameServiceImpl;
import hotciv.standard.Broker.Proxies.TileProxy;
import hotciv.standard.Broker.BrokerStubs.StubTileBroker;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestTileBroker {
  private Tile tile;

  @Before
  public void setUp() {
    Tile servant = new StubTileBroker();

    Invoker invoker = new TileInvoker(new NameServiceImpl());

    ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

    Requestor requestor = new StandardJSONRequestor(crh);

    tile = new TileProxy("3", requestor);
  }

  @Test
  public void shouldRequestTypeString() {
    assertThat(tile.getTypeString(), is(GameConstants.PLAINS));
  }
}
