package hotciv.standard.Broker.Invokers;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Unit;

public class UnitInvoker implements Invoker {
  private Unit unitStub;
  private Gson gson;

  public UnitInvoker(Unit servant) {
    this.gson = new Gson();
    unitStub = servant;
  }


  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
    Unit unit = lookUpUnit(objectId);
    return null;
  }

  private Unit lookUpUnit(String objectId) {
    return unitStub;
  }
}
