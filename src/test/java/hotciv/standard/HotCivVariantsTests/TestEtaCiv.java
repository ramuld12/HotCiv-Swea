package hotciv.standard.HotCivVariantsTests;


import hotciv.framework.*;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.EtaCivFactory;
import hotciv.standard.strategies.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestEtaCiv {
  private GameImpl game;

  /**
   * Fixture for EtaCiv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(new EtaCivFactory());
    assertThat(game, is(notNullValue()));
  }

  /**
   * Method for testing end of round triggers
   */
  public void endOfRound() {
    game.endOfTurn();
    game.endOfTurn();
  }

  /*
  @Test
  public void redCitySizedShouldIncrease1(){
    Position redCity = new Position(1,1);
    endOfRound();
    assertThat(game.getCityAt(redCity).getFoodAmount(), is(2));
  }*/

  @Test
  public void citySize1ShouldProduce1FoodPrRound(){
    Position redCity = new Position(1,1);
    endOfRound();
    assertTrue(game.getCityAt(redCity).getFoodAmount() == 1);
  }
}
