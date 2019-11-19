package hotciv.standard.Broker;

import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Game;

public class HotCivGameInvoker implements Invoker {

  public HotCivGameInvoker(Game servant) {

  }

  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {
    return null;
  }
}
