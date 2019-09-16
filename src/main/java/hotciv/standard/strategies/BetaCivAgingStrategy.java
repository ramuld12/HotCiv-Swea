package hotciv.standard.strategies;

import hotciv.standard.GameImpl;

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

    }
    return 1;
  }
}
// <