package hotciv.standard.strategies.AgingStrategies;

import hotciv.standard.GameImpl;
import hotciv.standard.strategies.AgingStrategies.AgingStrategy;

public class AlphaCivAgingStrategy implements AgingStrategy {

  @Override
  public int getAgeStep(GameImpl game) {
    return 100;
  }
}
