package hotciv.standard.Broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.Broker.Stubs.StubGame3;
import hotciv.stub.StubGame2;
import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class TestBroker {

  private Game game;

  @Before
  public void setUp() {
    Game servant = new StubGame3();
    GameObserver nullObserver = new NullObserver();
    servant.addObserver(nullObserver);

    Invoker invoker = new HotCivGameInvoker(servant);

    ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

    Requestor requestor = new StandardJSONRequestor(crh);

    game = new GameProxy(requestor);
    game.addObserver(nullObserver);
  }

  @Test
  public void gameAgeShouldBe42(){
    assertThat(game.getAge(), is(42));
  }


  private static class NullObserver implements GameObserver {
    @Override
    public void worldChangedAt(Position pos) {

    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {

    }

    @Override
    public void tileFocusChangedAt(Position position) {

    }
  }
}
