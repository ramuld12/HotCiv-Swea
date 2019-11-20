package hotciv.standard.Broker.GameLobby;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.Broker.BrokerConstants;
import hotciv.standard.Broker.Proxies.GameProxy;

public class HotCivStoryTest {

  public static void main(String[] args){
    new HotCivStoryTest(args);
  }

  private HotCivStoryTest(String[] args){
    HotCivManualClientTest client = new HotCivManualClientTest("localhost");
    TestSimpleMethods(client.getGame());
  }

  private static void TestSimpleMethods(Game game) {
    System.out.println("===Test of simple methods===");
    System.out.println("GameAge is: " + game.getAge());
    System.out.println("Game winner is: " + game.getWinner());
    System.out.println("The player in turn is: "+ game.getPlayerInTurn());
    System.out.println("Move a unit is: " + game.moveUnit(new Position(1,1), new Position(1,2)));

    System.out.println("Ending the turn");
    game.endOfTurn();
    System.out.println("New player in turn is: " + game.getPlayerInTurn());
  }

}
