package hotciv.standard.HotCivVariantsTests;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.GammaCivFactory;
import hotciv.standard.HotCivFactory.ThetaCivFactory;
import hotciv.standard.UnitImpl;
import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestThetaCiv {
  private GameImpl game;

  /**
   * Fixture for ThetaCiv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(new ThetaCivFactory());
    assertThat(game, is(notNullValue()));
  }

  @Test
  public void B52ShouldHave8DefenseAnd1Attack() {
    HashMap<Position, UnitImpl> units = game.getUnits();
    UnitImpl firstB52Ever = new UnitImpl(GameConstants.B52,Player.RED);
    Position B52Position = new Position(7,8);
    units.put(B52Position,firstB52Ever);
    assertThat(game.getUnitAt(B52Position).getDefensiveStrength(), is(8));
    assertThat(game.getUnitAt(B52Position).getAttackingStrength(), is(1));
  }



}

