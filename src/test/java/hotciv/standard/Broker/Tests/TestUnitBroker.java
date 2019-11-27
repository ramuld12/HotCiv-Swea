package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerStubs.StubGame3Broker;
import hotciv.standard.Broker.Invokers.RootInvoker;
import hotciv.standard.Broker.Invokers.UnitInvoker;
import hotciv.standard.Broker.LocalMethodClientRequestHandler;
import hotciv.standard.Broker.NameServiceImpl;
import hotciv.standard.Broker.NullObserver;
import hotciv.standard.Broker.Proxies.GameProxy;
import hotciv.standard.Broker.Proxies.UnitProxy;
import hotciv.standard.Broker.BrokerStubs.StubUnitBroker;
import hotciv.standard.GameObserverImpl;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestUnitBroker {
  private Game game;
  private Unit unit;

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
    this.unit = game.getUnitAt(new Position(1,1));
  }

  @Test
  public void shouldReturnArcher(){
    assertThat(unit.getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void shouldReturnRedPlayer(){
    assertThat(unit.getOwner(), is(Player.RED));
  }

  @Test
  public void shouldReturn42(){
    assertThat(unit.getMoveCount(), is(42));
  }

  @Test
  public void shouldReturnDefence10(){
    assertThat(unit.getDefensiveStrength(), is(10));
  }

  @Test
  public void shouldReturnAttack10(){
    assertThat(unit.getAttackingStrength(), is(10));
  }

  @Test
  public void shouldReturn10(){
    assertFalse(unit.isMoveable());
  }
}
