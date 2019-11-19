package hotciv.standard.Broker;

import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Game;

public class HotCivGameInvoker implements Invoker {

  private Game servant;

  public HotCivGameInvoker(Game servant) {
    this.servant = servant;

  }

  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload)
  {

    return new ReplyObject(Integer.valueOf(objectId),operationName);
  }
}
