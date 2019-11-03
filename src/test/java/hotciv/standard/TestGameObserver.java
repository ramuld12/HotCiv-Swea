package hotciv.standard;

import hotciv.framework.Player;
import hotciv.standard.HotCivFactory.AlphaCivFactory;
import hotciv.standard.TestStubs.GameObserverImplTest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestGameObserver {
  private GameImpl game;
  private GameObserverImplTest observer;
  private TestUtility util;

  @Before
  public void setUp() {
    observer = new GameObserverImplTest();
    game = new GameImpl(new AlphaCivFactory(), observer);
    assertThat(game, is(notNullValue()));
    util = new TestUtility(game);
  }

  @Test
  public void shouldCallEndOfTurnWithRedPlayerInTurn() {
    game.endOfTurn();
    assertThat(observer.getEndOfTurns., is(Player.RED));
  }
}
