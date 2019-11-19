package hotciv.standard.Broker;

import com.google.gson.Gson;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Game;

public class HotCivGameInvoker implements Invoker {
  private Gson gson;
  private Game servant;

  public HotCivGameInvoker(Game servant) {
    this.servant = servant;
    this.gson = new Gson();
  }

  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
    switch (operationName) {
      case BrokerConstants.getAgeString: { return new ReplyObject(200, gson.toJson(servant.getAge())); }
    }
    return null;
  }
}