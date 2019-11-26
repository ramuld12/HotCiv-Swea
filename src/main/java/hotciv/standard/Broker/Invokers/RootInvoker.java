package hotciv.standard.Broker.Invokers;

import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.NameService;
import hotciv.standard.Broker.NameServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class RootInvoker implements Invoker {

  private final NameService nameService;
  private final HashMap<String, Invoker> invokerMap;

  public RootInvoker(Game servant) {
    this.nameService = new NameServiceImpl();
    this.invokerMap = new HashMap<>();

    invokerMap.put(BrokerConstants.GAME_PREFIX, new GameInvoker(servant, nameService));
    invokerMap.put(BrokerConstants.CITY_PREFIX, new CityInvoker(nameService));
    invokerMap.put(BrokerConstants.UNIT_PREFIX, new UnitInvoker(nameService));
    invokerMap.put(BrokerConstants.TILE_PREFIX, new TileInvoker(nameService));
  }


  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
    String type = operationName.substring(0,4);
    Invoker subinvoker = invokerMap.get(type);

    try{
      //System.out.println(operationName);
      return subinvoker.handleRequest(objectId,operationName,payload);
    } catch (IllegalArgumentException e){
      return new ReplyObject(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }
  }
}
