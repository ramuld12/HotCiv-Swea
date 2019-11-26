package hotciv.standard.Broker.Invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.NameService;
import hotciv.standard.Broker.NameServiceImpl;

public class GameInvoker implements Invoker {
  private Gson gson;
  private Game servant;
  private NameService nameService;

  public GameInvoker(Game servant, NameService nameService) {
    this.gson = new Gson();
    this.servant = servant;
    this.nameService = nameService;
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

      //Object References
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
      case BrokerConstants.GAME_GET_CITY_METHOD: {
        Position cityPosition = gson.fromJson(jsonArray.get(0), Position.class);
        nameService.putCity(objectId, servant.getCityAt(cityPosition));
        return new ReplyObject(BrokerConstants.ok_status, gson.toJson(objectId));
      }

      case BrokerConstants.GAME_GET_UNIT_METHOD: {
        Position unitPosition = gson.fromJson(jsonArray.get(0), Position.class);
        nameService.putUnit(objectId, servant.getUnitAt(unitPosition));
        return new ReplyObject(BrokerConstants.ok_status, gson.toJson(objectId));
      }

      case BrokerConstants.GAME_GET_TILE_METHOD: {
        Position tilePosition = gson.fromJson(jsonArray.get(0), Position.class);
        nameService.putTile(objectId, servant.getTileAt(tilePosition));
        return new ReplyObject(BrokerConstants.ok_status, gson.toJson(objectId));
      }
    }
    return null;
  }
}