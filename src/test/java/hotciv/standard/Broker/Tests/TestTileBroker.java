package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerStubs.StubGame3Broker;
import hotciv.standard.Broker.Invokers.RootInvoker;
import hotciv.standard.Broker.Invokers.TileInvoker;
import hotciv.standard.Broker.LocalMethodClientRequestHandler;
import hotciv.standard.Broker.NameServiceImpl;
import hotciv.standard.Broker.NullObserver;
import hotciv.standard.Broker.Proxies.GameProxy;
import hotciv.standard.Broker.Proxies.TileProxy;
import hotciv.standard.Broker.BrokerStubs.StubTileBroker;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestTileBroker {
  private Tile tile;
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
    this.tile = game.getTileAt(new Position(1,1));
  }

  @Test
  public void shouldRequestTypeString() {
    assertThat(tile.getTypeString(), is(GameConstants.PLAINS));
  }
}
