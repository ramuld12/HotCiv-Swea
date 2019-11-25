package hotciv.standard.Broker.Proxies;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.FutureGame;

public class FutureGameProxy implements FutureGame, ClientProxy {
  private final String objectID;
  private final Requestor requestor;

  public FutureGameProxy(String objectID, Requestor requestor) {
    this.objectID = objectID;
    this.requestor = requestor;
  }

  @Override
  public String getId() {
    return objectID;
  }

  @Override
  public FutureGame createGame(String playerName, int playerNumber) {
     String id = requestor.sendRequestAndAwaitReply(
            "none", BrokerConstants.GAMELOBBY_CREATE_GAME_METHOD, String.class, playerName, playerNumber);
    return new FutureGameProxy(id, requestor);
  }
}
