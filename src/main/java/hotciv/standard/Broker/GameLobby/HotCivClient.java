package hotciv.standard.Broker.GameLobby;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.Proxies.GameProxy;
import java.util.*;

import java.io.IOException;

public class HotCivClient {
  private String operation;
  private String name;
  private String hostName;
  private String objectId;
  private Game game;

  public static void main(String[] args) throws IOException {
    new HotCivClient(args);
  }

  public HotCivClient(String[] args) {
    parseCommandlineParameters(args);
    System.out.println("LobbyClient: Asked to do operation "+operation+" for player "+name);
    ClientRequestHandler clientRequestHandler
          = new SocketClientRequestHandler("localhost", BrokerConstants.serverPort);
    Requestor requestor = new StandardJSONRequestor(clientRequestHandler);

  game = new GameProxy("", requestor);
  }

  private void parseCommandlineParameters(String[] args) {
    if (args.length < 4) {
      explainAndFail();
    }
    operation = args[0];
    name = args[1];
    objectId = args[2];
    hostName = args[3];
  }

  private static void explainAndFail() {
    System.out.println("Usage: LobbyClient <operation> <name> <objectId> <host>");
    System.out.println("  operation is either 'create' or 'join' or 'move'");
    System.out.println("  objectId is only used in join or move");
    System.out.println("    for join, it is the joinToken");
    System.out.println("    for move, it is the game's objectId");

  }
}
