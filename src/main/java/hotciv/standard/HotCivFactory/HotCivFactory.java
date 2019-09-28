package hotciv.standard.HotCivFactory;

import hotciv.standard.strategies.*;

public interface HotCivFactory {

  public AgingStrategy createAgingStrategy();

  public BattleStrategy createBattleStrategy();

  public UnitActionStrategy createUnitActionStrategy();

  public WinningStrategy createWinningStrategy();

  public WorldLayoutStrategy createWorldLayoutStrategy();

  public WorkForceFocusStrategy createWorkForceFocuesStrategy();

  public PopulationGrowthStrategy createPopulationGrowthStrategy();


}
