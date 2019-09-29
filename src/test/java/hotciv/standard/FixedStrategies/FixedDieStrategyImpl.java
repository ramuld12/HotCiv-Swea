package hotciv.standard.FixedTests;

import hotciv.standard.strategies.DieStrategy;

public class FixedDieStrategyImpl implements DieStrategy {
  @Override
  public int die() {
    return 1;
  }
}
