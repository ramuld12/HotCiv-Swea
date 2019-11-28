package hotciv.standard.Broker.Invokers;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Unit;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.NameService;

import javax.servlet.http.HttpServletResponse;

public class UnitInvoker implements Invoker {
  private final NameService nameService;
  private Gson gson;

  public UnitInvoker(NameService nameService) {
    this.gson = new Gson();
    this.nameService = nameService;
  }

  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
    Unit unit = lookUpUnit(objectId);

    switch (operationName) {

      case BrokerConstants.getTypeString : {
        return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(unit.getTypeString()));
      }

      case BrokerConstants.getOwnerUnit : {
        return new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(unit.getOwner()));
      }

      case BrokerConstants.getMoveCountString : {
        return new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(unit.getMoveCount()));
      }

      case BrokerConstants.getDefensiveStrengthString : {
        return new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(unit.getDefensiveStrength()));
      }

      case BrokerConstants.getAttackingStrengthString : {
        return new ReplyObject(HttpServletResponse.SC_OK,gson.toJson(unit.getAttackingStrength()));
      }
      case BrokerConstants.isUnitMoveable: {
        return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(unit.isMoveable()));
      }
    }
    return null;
  }

  private Unit lookUpUnit(String objectId) {
    return nameService.getUnit(objectId);
  }
}
