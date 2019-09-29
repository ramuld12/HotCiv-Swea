package hotciv.standard.TestStubs;

import hotciv.standard.FixedTests.FixedDieStrategyImpl;
import hotciv.standard.HotCivFactory.HotCivFactory;
import hotciv.standard.strategies.*;

public class FixedEpsilonCivFactory implements HotCivFactory {
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
    return new AlphaCivWorldLayoutStrategy();
  }

  @Override
  public WorkForceFocusStrategy createWorkForceFocuesStrategy() {
    return new AlphaCivWorkForceFocusStrategy();
  }

  @Override
  public PopulationGrowthStrategy createPopulationGrowthStrategy() { return new AlphaCivPopulationGrowthStrategy(); }

  ;
}
