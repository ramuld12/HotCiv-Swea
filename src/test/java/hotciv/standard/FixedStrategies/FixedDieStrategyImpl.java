package hotciv.standard.FixedStrategies;

import hotciv.standard.strategies.DieStrategies.DieStrategy;

public class FixedDieStrategyImpl implements DieStrategy {
  @Override
  public int die() {
    return 3;
  }
}
