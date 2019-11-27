package hotciv.standard.Broker.GameLobby;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.Proxies.GameProxy;
import hotciv.visual.CompositionTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.SelectionTool;

import java.util.*;

import java.io.IOException;

public class HotCivClient {
  private String operation;
  private String name;
  private String hostName;
  private String objectId;

  public static void main(String[] args) throws IOException {
    new HotCivClient(args);
  }

  public HotCivClient(String[] args) {
    //parseCommandlineParameters(args);
    System.out.println("LobbyClient: Asked to do operation "+operation+" for player "+name);
    ClientRequestHandler clientRequestHandler
          = new SocketClientRequestHandler("localhost", BrokerConstants.serverPort);

    Requestor requestor = new StandardJSONRequestor(clientRequestHandler);

    Game game = new GameProxy("", requestor);

    DrawingEditor editor =
            new MiniDrawApplication( "SemiCiv",
                    new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Playable SemiCiv");
    editor.setTool( new CompositionTool(game,new SelectionTool(editor)) );
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
