package hotciv.standard.Broker.GameLobby;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.Proxies.GameProxy;

public class HotCivManualClientTest{


  private Game game;

  public static void main(String[] args) throws Exception {
    new HotCivManualClientTest(args[0]);
  }

  public HotCivManualClientTest(String hostName) {
    System.out.println("=== HotCiv MANUAL TEST client (Socket) (host: " + hostName +")===");

    ClientRequestHandler clientRequestHandler
            = new SocketClientRequestHandler();
    clientRequestHandler.setServer(hostName, 0);
    Requestor requestor = new StandardJSONRequestor(clientRequestHandler);

    game = new GameProxy(requestor);
  }
  public Game getGame() {
    return game;
  }
}
