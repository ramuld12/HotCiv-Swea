package hotciv.standard.strategies;

import hotciv.framework.Player;

public class AlphaCivAgingStrategy implements AgingStrategy{

  @Override
  public int getAgeStep() {
    return 100;
  }
}
