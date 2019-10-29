package hotciv.standard.TestStubs;

import hotciv.standard.FixedStrategies.FixedDieStrategyImpl;
import hotciv.standard.HotCivFactory.HotCivFactory;
import hotciv.standard.adapters.WorldGeneratorAdapter;
import hotciv.standard.strategies.AgingStrategies.AgingStrategy;
import hotciv.standard.strategies.AgingStrategies.AlphaCivAgingStrategy;
import hotciv.standard.strategies.BattleStrategies.BattleStrategy;
import hotciv.standard.strategies.BattleStrategies.EpsilonCivBattleStrategy;
import hotciv.standard.strategies.PopulationStrategies.AlphaCivPopulationGrowthStrategy;
import hotciv.standard.strategies.PopulationStrategies.PopulationGrowthStrategy;
import hotciv.standard.strategies.UnitActionStrategies.AlphaCivUnitActionStrategy;
import hotciv.standard.strategies.UnitActionStrategies.UnitActionStrategy;
import hotciv.standard.strategies.WinningStrategies.EpsilonCivWinningStrategy;
import hotciv.standard.strategies.WinningStrategies.WinningStrategy;
import hotciv.standard.strategies.WorkFocusStrategies.AlphaCivWorkForceFocusStrategy;
import hotciv.standard.strategies.WorkFocusStrategies.WorkForceFocusStrategy;
import hotciv.standard.strategies.WorldLayoutStrategies.AlphaCivWorldLayoutStrategy;
import hotciv.standard.strategies.WorldLayoutStrategies.WorldLayoutStrategy;

public class ThirdPartyWorldLayoutFactory implements HotCivFactory {
  @Override
  public AgingStrategy createAgingStrategy() {
    return new AlphaCivAgingStrategy();
  }

  @Override
  public BattleStrategy createBattleStrategy() {
    return new EpsilonCivBattleStrategy(new FixedDieStrategyImpl());
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
    return new WorldGeneratorAdapter();
  }

  @Override
  public WorkForceFocusStrategy createWorkForceFocuesStrategy() {
    return new AlphaCivWorkForceFocusStrategy();
  }

  @Override
  public PopulationGrowthStrategy createPopulationGrowthStrategy() { return new AlphaCivPopulationGrowthStrategy(); }
}
