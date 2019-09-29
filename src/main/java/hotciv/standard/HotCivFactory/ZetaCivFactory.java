package hotciv.standard.HotCivFactory;

import hotciv.standard.strategies.*;

public class ZetaCivFactory implements HotCivFactory {
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
    return new AlphaCivUnitActionStrategy();
  }

  @Override
  public WinningStrategy createWinningStrategy() {
    return new ZetaCivWInningStrategy();
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
