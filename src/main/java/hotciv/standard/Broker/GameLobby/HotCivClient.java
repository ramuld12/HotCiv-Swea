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
    System.out.println("LobbyClient: Asked to do operation "+operation+" for player "+name);
    ClientRequestHandler clientRequestHandler
          = new SocketClientRequestHandler("localhost", BrokerConstants.serverPort);
    Requestor requestor = new StandardJSONRequestor(clientRequestHandler);
    Game game = new GameProxy("Game", requestor);

    DrawingEditor editor =
            new MiniDrawApplication( "Playable SemiCiv",
                    new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Play SemiCiv");
    editor.setTool( new CompositionTool(game,new SelectionTool(editor)) );
  }
}
