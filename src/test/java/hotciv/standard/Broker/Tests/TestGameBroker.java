package hotciv.standard.Broker.Tests;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.standard.Broker.Invokers.GameInvoker;
import hotciv.standard.Broker.LocalMethodClientRequestHandler;
import hotciv.standard.Broker.Proxies.GameProxy;
import hotciv.standard.Broker.SpyRequester;
import hotciv.standard.Broker.Stubs.StubGame3;
import org.apache.http.annotation.ThreadSafe;
import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;


public class TestGameBroker {

  private Game game;
  private SpyRequester SpyRequester;

  @Before
  public void setUp() {
    Game servant = new StubGame3();
    GameObserver nullObserver = new NullObserver();
    servant.addObserver(nullObserver);

    Invoker invoker = new GameInvoker(servant);

    ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);

    Requestor requestor = new StandardJSONRequestor(crh);

    game = new GameProxy(requestor);
    game.addObserver(nullObserver);
    SpyRequester = new SpyRequester();
  }

  @Test
  public void gameAgeShouldBe42(){
    assertThat(game.getAge(), is(42));
  }

  @Test
  public void shouldBeRedAsWinner() {
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void shouldBeBlueAsPlayerInTurn() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBeAllowedToMoveUnitFrom1_1To1_2() {
    assertTrue(game.moveUnit(new Position(1,1), new Position(1,2)));
  }

  @Test
  public void playerInTurnShouldAlternateWhenEndingTurn() {
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldChangeProcutionInCityAt1_1ToArcher() {
    Position cityPos = new Position(1,1);
    game.changeProductionInCityAt(cityPos, GameConstants.ARCHER);
    assertThat(SpyRequester.lastArgument[1], is(GameConstants.ARCHER));
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
