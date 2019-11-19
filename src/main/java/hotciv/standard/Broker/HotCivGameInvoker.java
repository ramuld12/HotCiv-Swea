package hotciv.standard.Broker;

import com.google.gson.Gson;
import com.sun.corba.se.pept.broker.Broker;
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
      case BrokerConstants.getAgeString: {
        System.out.println(servant.getAge());
        return new ReplyObject(200, gson.toJson(servant.getAge()));
      }
    }
    return new ReplyObject(BrokerConstants.ok_status, gson.toJson(servant.getAge()));
  }
}