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

  @Test
  public void cityWithProductionB52ShouldAB52(){
    Position cityPosition = new Position(1,1);
    game.changeProductionInCityAt(cityPosition, GameConstants.B52);
    game.getCityAt(cityPosition).setTreas(61);
    game.produceUnitInCityAt(cityPosition,game.getCityAt(cityPosition));
    assertThat(game.getUnitAt(cityPosition).getTypeString(), is(GameConstants.B52));
  }

  @Test
  public void shouldCost60ToProduceB52Unit(){
    Position cityPosition = new Position(1,1);
    game.changeProductionInCityAt(cityPosition, GameConstants.B52);
    game.getCityAt(cityPosition).setTreas(61);
    game.produceUnitInCityAt(cityPosition,game.getCityAt(cityPosition));
    assertTrue(game.getCityAt(cityPosition).getTreasury()==1);
    assertThat(game.getUnitAt(cityPosition).getTypeString(), is(GameConstants.B52));
  }

  @Test
  public void B52ShouldTravelDistance(){
      Position p1 = new Position(2,0); Position p2 = new Position(3,0); Position p3 = new Position(4,0);
      game.createUnitAtPosition(p1,GameConstants.B52,Player.RED);
      game.movingTheUnit(p1, p2);
      game.movingTheUnit(p2,p3);
      assertThat(game.getUnitAt(p3).getTypeString(), is(GameConstants.B52));
  }

  @Test
  public void B52ShouldTravelOverAllTileTypes(){
    Position p1 = new Position(2,1); Position p2 = new Position(3,2);
    Position mountainPosition = new Position(2,2);
    game.createUnitAtPosition(p1,GameConstants.B52,Player.RED);
    assertThat(game.getTileAt(mountainPosition).getTypeString(), is(GameConstants.MOUNTAINS));
    game.moveUnit(p1, mountainPosition);
    assertThat(game.getUnitAt(mountainPosition).getTypeString(),  is(GameConstants.B52));
  }

  @Test
  public void shouldRemoveCityWhenActionPerformed(){
    Position cityPosition = new Position(1,1);
    game.createUnitAtPosition(cityPosition,GameConstants.B52,Player.BLUE);
    game.performUnitActionAt(cityPosition);
    assertNull(game.getCityAt(cityPosition));
  }

  @Test
  public void shouldDecreasePopulationOfCityAfterAttack(){
    
  }
}

