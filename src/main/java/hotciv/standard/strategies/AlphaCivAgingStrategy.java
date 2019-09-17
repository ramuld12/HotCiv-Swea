package hotciv.standard.strategies;

import hotciv.standard.GameImpl;

public class AlphaCivAgingStrategy implements AgingStrategy{

  @Override
  public int getAgeStep(GameImpl game) {
    return 100;
  }
}
