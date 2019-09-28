package hotciv.standard.HotCivFactory;

import hotciv.standard.strategies.*;

public class BetaCivFactory implements HotCivFactory {


  @Override
  public AgingStrategy createAgingStrategy() {
    return new BetaCivAgingStrategy();
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
    return new BetaCivWinningStrategy();
  }

  @Override
  public WorldLayoutStrategy createWorldLayoutStrategy() {
    return new AlphaCivWorldLayoutStrategy();
  }

  @Override
  public WorkForceFocusStrategy createWorkForceFocuesStrategy() {
    return null;
  }
}
