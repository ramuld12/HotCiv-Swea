package hotciv.standard.Broker.Invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.sun.corba.se.pept.broker.Broker;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.Broker.BrokerConstants;

public class GameInvoker implements Invoker {
  private Gson gson;
  private Game servant;

  public GameInvoker(Game servant) {
    this.gson = new Gson();
    this.servant = servant;
  }

  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
    JsonParser parser = new JsonParser();
    JsonArray jsonArray = parser.parse(payload).getAsJsonArray();

    switch (operationName) {
      //Simple accessors
      case BrokerConstants.getPlayerInTurnString: { return new ReplyObject(BrokerConstants.ok_status, gson.toJson(servant.getPlayerInTurn())); }
      case BrokerConstants.getWinnerString:       { return new ReplyObject(BrokerConstants.ok_status, gson.toJson(servant.getWinner())); }
      case BrokerConstants.getAgeString:          { return new ReplyObject(BrokerConstants.ok_status, gson.toJson(servant.getAge())); }

      //Booleans
      case BrokerConstants.moveUnit_action: {
        Position from = gson.fromJson(jsonArray.get(0), Position.class);
        Position to = gson.fromJson(jsonArray.get(1), Position.class);
        return new ReplyObject(BrokerConstants.ok_status, gson.toJson(servant.moveUnit(from,to)));
        }

      //Voids
      case BrokerConstants.endTurn_action: {
        servant.endOfTurn();
        return new ReplyObject(BrokerConstants.ok_status, "");
      }
      case BrokerConstants.changeCityProduction: {
        Position cityPositition = gson.fromJson(jsonArray.get(0), Position.class);
        String unitType = gson.fromJson(jsonArray.get(1), String.class);
        servant.changeProductionInCityAt(cityPositition, unitType);
        return new ReplyObject(BrokerConstants.ok_status, "");
      }
      case BrokerConstants.unitAction: {
        Position actionPosition = gson.fromJson(jsonArray.get(0), Position.class);
        servant.performUnitActionAt(actionPosition);
        return new ReplyObject(BrokerConstants.ok_status, "");
      }
    }
    return null;
  }
}