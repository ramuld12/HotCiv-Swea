package hotciv.standard.HotCivFactory;

import hotciv.standard.strategies.AgingStrategies.AgingStrategy;
import hotciv.standard.strategies.BattleStrategies.BattleStrategy;
import hotciv.standard.strategies.PopulationStrategies.PopulationGrowthStrategy;
import hotciv.standard.strategies.UnitActionStrategies.UnitActionStrategy;
import hotciv.standard.strategies.WinningStrategies.WinningStrategy;
import hotciv.standard.strategies.WorkFocusStrategies.WorkForceFocusStrategy;
import hotciv.standard.strategies.WorldLayoutStrategies.WorldLayoutStrategy;

public interface HotCivFactory {

  public AgingStrategy createAgingStrategy();

  public BattleStrategy createBattleStrategy();

  public UnitActionStrategy createUnitActionStrategy();

  public WinningStrategy createWinningStrategy();

  public WorldLayoutStrategy createWorldLayoutStrategy();

  public WorkForceFocusStrategy createWorkForceFocuesStrategy();

  public PopulationGrowthStrategy createPopulationGrowthStrategy();


}
