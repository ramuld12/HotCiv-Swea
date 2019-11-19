package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.standard.Broker.Invokers.UnitInvoker;
import hotciv.standard.Broker.LocalMethodClientRequestHandler;
import hotciv.standard.Broker.Proxies.UnitProxy;
import hotciv.standard.Broker.BrokerStubs.StubUnitBroker;
import org.junit.*;

public class TestUnitBroker {
  private Unit Unit;

  @Before
  public void setUp() {
    Unit servant = new StubUnitBroker();

    Invoker invoker = new UnitInvoker(servant);

    ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

    Requestor requestor = new StandardJSONRequestor(crh);

    Unit = new UnitProxy(requestor);
  }
}
