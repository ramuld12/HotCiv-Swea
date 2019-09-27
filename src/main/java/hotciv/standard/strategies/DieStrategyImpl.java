package hotciv.standard.strategies;

import java.util.Random;

public class DieStrategyImpl implements DieStrategy {
  @Override
  public int die() {
    Random rand = new Random();
    return rand.nextInt(6)+1;
  }
}
