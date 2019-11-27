package hotciv.standard.Broker.GameLobby;

import frds.broker.Invoker;
import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.framework.Game;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.BrokerStubs.StubGame3Broker;
import hotciv.standard.Broker.Invokers.GameInvoker;
import hotciv.standard.Broker.Invokers.RootInvoker;
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
    //Game game = new StubGame3Broker();

    Invoker invoker = new RootInvoker(game, new GameObserverImpl());

    SocketServerRequestHandler ssrh = new SocketServerRequestHandler(port, invoker);

    System.out.println("=== GameLobby Socket based Server Request Handler (port:"
            + port + ") ===");
    System.out.println(" Use ctrl-c to terminate!");
    ssrh.start();
  }
}
