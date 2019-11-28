package hotciv.standard.Broker.Invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.NameService;

import javax.servlet.http.HttpServletResponse;

public class GameInvoker implements Invoker {
  private Gson gson;
  private Game servant;
  private NameService nameService;

  private final GameObserver gameObserver;

  public GameInvoker(Game servant, NameService nameService, GameObserver gameObserver) {
    this.gson = new Gson();
    this.servant = servant;
    this.nameService = nameService;
    this.gameObserver = gameObserver;
  }

  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
    JsonParser parser = new JsonParser();
    JsonArray jsonArray = parser.parse(payload).getAsJsonArray();

    switch (operationName) {
      //Simple accessors
      case BrokerConstants.getPlayerInTurnString: { return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getPlayerInTurn())); }
      case BrokerConstants.getWinnerString:       { return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getWinner())); }
      case BrokerConstants.getAgeString:          { return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.getAge())); }

      //Booleans
      case BrokerConstants.moveUnit_action: {
        Position from = gson.fromJson(jsonArray.get(0), Position.class);
        Position to = gson.fromJson(jsonArray.get(1), Position.class);
        return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(servant.moveUnit(from,to)));
        }

      //Voids
      case BrokerConstants.endTurn_action: {
        servant.endOfTurn();
        return new ReplyObject(HttpServletResponse.SC_OK, "");
      }

      case BrokerConstants.changeCityProduction: {
        Position cityPositition = gson.fromJson(jsonArray.get(0), Position.class);
        String unitType = gson.fromJson(jsonArray.get(1), String.class);
        servant.changeProductionInCityAt(cityPositition, unitType);
        return new ReplyObject(HttpServletResponse.SC_OK, "");
      }
      case BrokerConstants.unitAction: {
        Position actionPosition = gson.fromJson(jsonArray.get(0), Position.class);
        servant.performUnitActionAt(actionPosition);
        return new ReplyObject(HttpServletResponse.SC_OK, "");
      }

      //Object References
      case BrokerConstants.GAME_GET_CITY_METHOD: {
        Position cityPosition = gson.fromJson(jsonArray.get(0), Position.class);
        City cityRef = servant.getCityAt(cityPosition);
        if (cityRef == null) {
          return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(""));
        }
        String cityId = cityRef.getId();
        nameService.putCity(cityId, cityRef);
        return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(cityId));
      }

      case BrokerConstants.GAME_GET_UNIT_METHOD: {
        Position unitPosition = gson.fromJson(jsonArray.get(0), Position.class);
        Unit unitRef = servant.getUnitAt(unitPosition);
        if (unitRef == null) {
          return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(""));
        }
        String unitId = unitRef.getId();
        nameService.putUnit(unitId, unitRef);
        return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(unitId));
      }

      case BrokerConstants.GAME_GET_TILE_METHOD: {
        Position tilePosition = gson.fromJson(jsonArray.get(0), Position.class);
        nameService.putTile(objectId, servant.getTileAt(tilePosition));
        return new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(objectId));
      }
    }
    return null;
  }
}