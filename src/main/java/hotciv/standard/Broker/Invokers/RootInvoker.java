package hotciv.standard.Broker.Invokers;

import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Unit;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.NameService;
import hotciv.standard.Broker.NameServiceImpl;

public class RootInvoker implements Invoker {

  private final NameService nameService;
  private final Game servant;
  private final Invoker invoker;

  public RootInvoker(Game servant) {
    this.nameService = new NameServiceImpl();
    this.servant = servant;
     invoker = new GameInvoker(servant, nameService);
  }


  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
      String type = operationName.substring(0,3);

      switch (type) {
        case BrokerConstants.GAME_TYPE : new GameInvoker(servant,nameService).handleRequest(objectId,operationName,payload);
        case BrokerConstants.CITY_TYPE : new CityInvoker(nameService).handleRequest(objectId,operationName,payload);
        case BrokerConstants.UNIT_TYPE : new UnitInvoker(nameService);
        case BrokerConstants.TILE_TYPE : new TileInvoker(nameService);
    }
    return null;
  }
}
