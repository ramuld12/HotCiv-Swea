package hotciv.standard.HotCivVariantsTests;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.GammaCivFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

//Skeleton class for GammaCiv test cases

public class TestGammaCiv {
  private GameImpl game;

  /** Fixture for GammaCiv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new GammaCivFactory());
    assertThat(game, is(notNullValue()));
  }

  @Test
  public void settlerActionShouldResultInACity(){
    Position settlerPosition = new Position(4,3);
    game.performUnitActionAt(settlerPosition);
    assertNotNull(game.getCityAt(settlerPosition));
  }

  @Test
  public void redSettlerActionShouldResultInARedCity(){
    Position settlerPosition = new Position(4,3);
    game.performUnitActionAt(settlerPosition);
    assertThat(game.getCityAt(settlerPosition).getOwner(), is(Player.RED));
  }

  @Test
  public void ArcherShouldHave3DefenseAnd2Attack() {
    Position archerPosition = new Position(2,0);
    assertThat(game.getUnitAt(archerPosition).getDefensiveStrength(), is(3));
    assertThat(game.getUnitAt(archerPosition).getAttackingStrength(), is(2));
  }

  @Test
  public void LegionShouldHave2DefenseAnd4Attack() {
    Position legionPosition = new Position(3,2);
    assertThat(game.getUnitAt(legionPosition).getDefensiveStrength(), is(2));
    assertThat(game.getUnitAt(legionPosition).getAttackingStrength(), is(4));
  }

  @Test
  public void SettlerShouldHave3DefenseAnd0Attack() {
    Position settlerPosition = new Position(4,3);
    assertThat(game.getUnitAt(settlerPosition).getDefensiveStrength(), is(3));
    assertThat(game.getUnitAt(settlerPosition).getAttackingStrength(), is(0));
  }

  @Test
  public void archerActionShouldDoubleDefenceStrength(){
    Position archerPosition = new Position(2,0);
    assertThat(game.getUnitAt(archerPosition).getDefensiveStrength(), is(3));
    game.performUnitActionAt(archerPosition);
    assertThat(game.getUnitAt(archerPosition).getDefensiveStrength(), is(6));
  }

  @Test
  public void fortifiedArcherShouldNotBeAbleToMove(){
    Position archerPosition = new Position(2,0);
    game.performUnitActionAt(archerPosition);
    assertThat(game.moveUnit(archerPosition, new Position(2,1)), is(false));
  }

  @Test
  public void shouldRemoveFortificationFromArcher() {
    Position archerPosition = new Position(2,0);
    game.performUnitActionAt(archerPosition);
    game.performUnitActionAt(archerPosition);
    assertThat(game.getUnitAt(archerPosition).getDefensiveStrength(), is(3));
    assertTrue(game.getUnitAt(archerPosition).isMoveable());
  }

}




