package hotciv.standard.HotCivFactory;

import hotciv.standard.strategies.AgingStrategies.AgingStrategy;
import hotciv.standard.strategies.AgingStrategies.AlphaCivAgingStrategy;
import hotciv.standard.strategies.BattleStrategies.BattleStrategy;
import hotciv.standard.strategies.BattleStrategies.EpsilonCivBattleStrategy;
import hotciv.standard.strategies.DieStrategies.DieStrategy;
import hotciv.standard.strategies.PopulationStrategies.PopulationGrowthStrategy;
import hotciv.standard.strategies.UnitActionStrategies.AlphaCivUnitActionStrategy;
import hotciv.standard.strategies.UnitActionStrategies.UnitActionStrategy;
import hotciv.standard.strategies.WinningStrategies.EpsilonCivWinningStrategy;
import hotciv.standard.strategies.WinningStrategies.WinningStrategy;
import hotciv.standard.strategies.WorkFocusStrategies.WorkForceFocusStrategy;
import hotciv.standard.strategies.WorldLayoutStrategies.DeltaCivWorldLayoutStrategy;
import hotciv.standard.strategies.WorldLayoutStrategies.WorldLayoutStrategy;

public class SemiCivFactory implements HotCivFactory {
  @Override
  public AgingStrategy createAgingStrategy() { return new AlphaCivAgingStrategy();}

  @Override
  public BattleStrategy createBattleStrategy() {
    return new EpsilonCivBattleStrategy(new DieStrategy() {
      @Override
      public int die() {
        return 1; // for testing returning this result temporarily (to be changed to random)
      }
    });
  }

  @Override
  public UnitActionStrategy createUnitActionStrategy() {
    return new AlphaCivUnitActionStrategy();
  }

  @Override
  public WinningStrategy createWinningStrategy() {
    return new EpsilonCivWinningStrategy();
  }

  @Override
  public WorldLayoutStrategy createWorldLayoutStrategy() {
    return new DeltaCivWorldLayoutStrategy();
  }

  @Override
  public WorkForceFocusStrategy createWorkForceFocuesStrategy() {
    return null;
  }

  @Override
  public PopulationGrowthStrategy createPopulationGrowthStrategy() {
    return null;
  }
}
