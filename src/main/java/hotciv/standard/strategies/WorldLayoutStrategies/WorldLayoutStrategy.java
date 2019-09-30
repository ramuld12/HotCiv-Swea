package hotciv.standard.strategies.WorldLayoutStrategies;

import hotciv.standard.GameImpl;

public interface WorldLayoutStrategy {

  /**
   * Creates the world according to
   * the given WorldLayoutStrategy
   * @param game the current game
   */
  public void createTheWorld(GameImpl game);

}
