package hotciv.standard.Broker.Invokers;

import frds.broker.Invoker;
import frds.broker.ReplyObject;
import hotciv.framework.Game;
import hotciv.standard.Broker.NameService;
import hotciv.standard.Broker.NameServiceImpl;

public class RootInvoker implements Invoker {

  private final NameService nameService;
  private final Game game;
  private final Invoker invoker;

  public RootInvoker(Game servant) {
    this.nameService = new NameServiceImpl();
    this.game = servant;
     invoker = new GameInvoker(servant, nameService);
  }


  @Override
  public ReplyObject handleRequest(String objectId, String operationName, String payload) {





    return invoker.handleRequest(objectId, operationName, payload);
  }
}
