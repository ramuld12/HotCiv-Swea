package hotciv.standard.strategies.AgingStrategies;

import hotciv.standard.GameImpl;

public interface AgingStrategy {

  /**
   * Returns the next ageStep according to the
   * given agingStrategy
   * @param game the current game
   * @return the next agestep as an int
   */
  public int getAgeStep(GameImpl game);
}
