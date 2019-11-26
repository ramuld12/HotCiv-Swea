package hotciv.standard.Broker.Invokers;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.BrokerStubs.StubCityBroker;
import hotciv.standard.Broker.NameService;

public class CityInvoker implements Invoker {
  private final NameService nameService;
  private Gson gson;

  public CityInvoker(NameService nameService){
    gson = new Gson();
    this.nameService = nameService;
  }


  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
    City city = lookUpCity(objectId);


    switch (operationName){

      case BrokerConstants.getOwnerCity : {
        return new ReplyObject(BrokerConstants.ok_status,gson.toJson(city.getOwner()));
      }
      case BrokerConstants.getSizeString : {
        return new ReplyObject(BrokerConstants.ok_status,gson.toJson(city.getSize()));
      }
      case BrokerConstants.getTreasuryString : {
        return new ReplyObject(BrokerConstants.ok_status,gson.toJson(city.getTreasury()));
      }
      case BrokerConstants.getProductionString : {
        return new ReplyObject(BrokerConstants.ok_status,gson.toJson(city.getProduction()));
      }
      case BrokerConstants.getWorkforceFocus : {
        return new ReplyObject(BrokerConstants.ok_status,gson.toJson(city.getWorkforceFocus()));
      }
    }
    return null;
  }

  private City lookUpCity(String objectId) {
    return nameService.getCity(objectId);
  }
}
