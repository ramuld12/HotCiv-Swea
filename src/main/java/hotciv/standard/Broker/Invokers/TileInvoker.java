package hotciv.standard.Broker.Invokers;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Tile;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.NameService;

public class TileInvoker implements Invoker {
  private final NameService nameService;
  private Gson gson;

  public TileInvoker(NameService nameService) {
    this.gson = new Gson();
    this.nameService = nameService;
  }

  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
    Tile tile = lookUpTile(objectId);

    if (operationName.equals(BrokerConstants.tileString)) {
      return new ReplyObject(BrokerConstants.ok_status, gson.toJson(tile.getTypeString()));
    }
    return null;
  }

  private Tile lookUpTile(String objectId) {return nameService.getTile(objectId); }
}
