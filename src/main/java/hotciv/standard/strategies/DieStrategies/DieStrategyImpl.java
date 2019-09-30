package hotciv.standard.strategies.DieStrategies;

import hotciv.standard.strategies.DieStrategies.DieStrategy;

import java.util.Random;

public class DieStrategyImpl implements DieStrategy {
  @Override
  public int die() {
    Random rand = new Random();
    return rand.nextInt(6)+1;
  }
}
