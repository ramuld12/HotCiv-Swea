package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.AlphaCivFactory;
import hotciv.standard.TestUtility;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.decorators.transcriptDecorator;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class TestTranscriptDecorator {
  private Game game;

  /** Fixture for betaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new AlphaCivFactory());
    assertThat(game, is(notNullValue()));
  }


}
