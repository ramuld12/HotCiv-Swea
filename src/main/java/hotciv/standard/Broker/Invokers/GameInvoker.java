package hotciv.standard.Broker.Invokers;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Game;
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
    switch (operationName) {
      case BrokerConstants.getAgeString: { return new ReplyObject(200, gson.toJson(servant.getAge())); }
    }
    return null;
  }
}