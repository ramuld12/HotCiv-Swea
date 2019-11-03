package hotciv.standard;

import hotciv.standard.HotCivFactory.AlphaCivFactory;
import hotciv.standard.TestStubs.GameObserverImplTest;
import org.junit.Before;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class TestGameObserver {
  private GameImpl game;
  private TestUtility util;

  @Before
  public void setUp() {
    game = new GameImpl(new AlphaCivFactory(), new GameObserverImplTest());
    assertThat(game, is(notNullValue()));
    util = new TestUtility(game);
  }
}
