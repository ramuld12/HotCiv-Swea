package hotciv.standard.Broker.GameLobby;

import frds.broker.Invoker;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.framework.Game;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.Invokers.GameInvoker;
import hotciv.standard.Broker.NameServiceImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.SemiCivFactory;
import hotciv.visual.CompositionTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.SelectionTool;
import hotciv.standard.GameObserverImpl;
public class HotCivServer {

  public static void main(String[] args) throws Exception {
    new HotCivServer();
  }

  public HotCivServer() {
    int port = BrokerConstants.serverPort;
    Game game = new GameImpl(new SemiCivFactory());
    DrawingEditor editor =
            new MiniDrawApplication( "Gui", new HotCivFactory4(game));
    editor.open();
    editor.showStatus("Playable SemiCiv");
    editor.setTool( new CompositionTool(game,new SelectionTool(editor)) );


    Invoker invoker = new GameInvoker(game, new NameServiceImpl(), new GameObserverImpl());

    SocketServerRequestHandler ssrh = new SocketServerRequestHandler(port, invoker);

    System.out.println("=== GameLobby Socket based Server Request Handler (port:"
            + port + ") ===");
    System.out.println(" Use ctrl-c to terminate!");
    ssrh.start();
  }
}
