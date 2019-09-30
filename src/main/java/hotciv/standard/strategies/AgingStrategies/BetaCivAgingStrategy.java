package hotciv.standard.strategies.AgingStrategies;

import hotciv.standard.GameImpl;
import hotciv.standard.strategies.AgingStrategies.AgingStrategy;

public class BetaCivAgingStrategy implements AgingStrategy {

  @Override
  public int getAgeStep(GameImpl game) {
    if (game.getAge() >= -4000 && game.getAge() < -100) {
      return 100;
    } else if (game.getAge() == -100) {
      return 99;
    } else if (game.getAge() == -1) {
      return 2;
    } else if (game.getAge() == 1) {
      return 49;
    } else if (game.getAge() >= 50 && game.getAge() < 1750 ){
      return 50;
    } else if (game.getAge() >= 1750 && game.getAge() < 1900){
      return 25;
    } else if (game.getAge() >= 1900 && game.getAge() < 1970){
      return 5;
    } else if (game.getAge() >= 1970){
      return 1;
    }
    return 1;
  }
}
// <