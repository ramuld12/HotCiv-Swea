package hotciv.standard.Broker.Invokers;

import com.google.gson.Gson;
import com.sun.corba.se.pept.broker.Broker;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Unit;
import hotciv.standard.Broker.BrokerConstants;

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

    switch (operationName) {

      case BrokerConstants.getTypeStringString : {
        return new ReplyObject(BrokerConstants.ok_status, gson.toJson(unit.getTypeString()));
      }

      case BrokerConstants.getOwnerString : {
        return new ReplyObject(BrokerConstants.ok_status,gson.toJson(unit.getOwner()));
      }

      case BrokerConstants.getMoveCountString : {
        return new ReplyObject(BrokerConstants.ok_status,gson.toJson(unit.getMoveCount()));
      }

      case BrokerConstants.getDefensiveStrengthString : {
        return new ReplyObject(BrokerConstants.ok_status,gson.toJson(unit.getDefensiveStrength()));
      }

      case BrokerConstants.getAttackingStrengthString : {
        return new ReplyObject(BrokerConstants.ok_status,gson.toJson(unit.getAttackingStrength()));
      }
    }
    return null;
  }

  private Unit lookUpUnit(String objectId) {
    return unitStub;
  }
}
