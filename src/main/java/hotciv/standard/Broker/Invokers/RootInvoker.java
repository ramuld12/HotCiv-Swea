package hotciv.standard.Broker.Invokers;

import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.NameService;
import hotciv.standard.Broker.NameServiceImpl;

public class RootInvoker implements Invoker {

  private final NameService nameService;
  private final Object servant;

  public RootInvoker(Object servant) {
    this.nameService = new NameServiceImpl();
    this.servant = servant;
  }


  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
      String type = operationName.substring(0,3);

      switch (type) {
        case BrokerConstants.GAME_TYPE : new GameInvoker((Game)servant,nameService).handleRequest(objectId,operationName,payload);
        case BrokerConstants.CITY_TYPE : new CityInvoker((City)servant, nameService).handleRequest(objectId,operationName,payload);
        case BrokerConstants.UNIT_TYPE : new UnitInvoker((Unit)servant, nameService);
        case BrokerConstants.TILE_TYPE : new TileInvoker((Tile)servant, nameService);
    }
    return null;
  }
}
