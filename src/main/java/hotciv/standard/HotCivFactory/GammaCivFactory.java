package hotciv.standard.HotCivFactory;

import hotciv.standard.strategies.AgingStrategies.AgingStrategy;
import hotciv.standard.strategies.AgingStrategies.AlphaCivAgingStrategy;
import hotciv.standard.strategies.BattleStrategies.AlphaCivBattleStrategy;
import hotciv.standard.strategies.BattleStrategies.BattleStrategy;
import hotciv.standard.strategies.PopulationStrategies.AlphaCivPopulationGrowthStrategy;
import hotciv.standard.strategies.PopulationStrategies.PopulationGrowthStrategy;
import hotciv.standard.strategies.UnitActionStrategies.GammaCivUnitActionStrategy;
import hotciv.standard.strategies.UnitActionStrategies.UnitActionStrategy;
import hotciv.standard.strategies.WinningStrategies.AlphaCivWinningStrategy;
import hotciv.standard.strategies.WinningStrategies.WinningStrategy;
import hotciv.standard.strategies.WorkFocusStrategies.AlphaCivWorkForceFocusStrategy;
import hotciv.standard.strategies.WorkFocusStrategies.WorkForceFocusStrategy;
import hotciv.standard.strategies.WorldLayoutStrategies.AlphaCivWorldLayoutStrategy;
import hotciv.standard.strategies.WorldLayoutStrategies.WorldLayoutStrategy;

public class GammaCivFactory implements HotCivFactory {
  @Override
  public AgingStrategy createAgingStrategy() {
    return new AlphaCivAgingStrategy();
  }

  @Override
  public BattleStrategy createBattleStrategy() {
    return new AlphaCivBattleStrategy();
  }

  @Override
  public UnitActionStrategy createUnitActionStrategy() {
    return new GammaCivUnitActionStrategy();
  }

  @Override
  public WinningStrategy createWinningStrategy() {
    return new AlphaCivWinningStrategy();
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


}
