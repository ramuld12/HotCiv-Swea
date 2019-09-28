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


  @Test
  public void redCitySize1ShouldProduce1FoodPrRound(){
    Position redCity = new Position(1,1);
    endOfRound();
    endOfRound();
    assertTrue(game.getCityAt(redCity).getFoodAmount() == 2);
  }

  @Test
  public void blueCitySize1ShouldProduce1FoodPrRound(){
    Position blueCity = new Position(4,1);
    endOfRound();
    endOfRound();
    assertTrue(game.getCityAt(blueCity).getFoodAmount() == 2);
  }

  @Test
  public void redCitySizedShouldIncreaseFrom1To2WhenFoodAmountReach8(){
    Position redCity = new Position(1,1);
    game.getCityAt(redCity).setFoodAmount(8);
    assertThat(game.getCityAt(redCity).getSize(), is(8));
  }

}
