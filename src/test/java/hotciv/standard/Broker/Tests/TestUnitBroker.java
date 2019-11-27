package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerStubs.StubCityBroker;
import hotciv.standard.Broker.Invokers.UnitInvoker;
import hotciv.standard.Broker.LocalMethodClientRequestHandler;
import hotciv.standard.Broker.NameServiceImpl;
import hotciv.standard.Broker.Proxies.UnitProxy;
import hotciv.standard.Broker.BrokerStubs.StubUnitBroker;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestUnitBroker {
  private Unit unit;

  @Before
  public void setUp() {
    Unit servant = new StubUnitBroker();
    Invoker invoker = new UnitInvoker(new NameServiceImpl());
    ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
    Requestor requestor = new StandardJSONRequestor(crh);
    unit = new UnitProxy("", requestor);
  }

  @Test
  public void shouldReturnArcher(){
    assertThat(unit.getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void shouldReturnRedPlayer(){
    StubCityBroker servant = new StubCityBroker();
    assertThat(servant.getOwner(), is(Player.RED));
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
