package hotciv.standard.Broker.BrokerStubs;

import frds.broker.Servant;
import hotciv.framework.*;

public class StubGame3Broker implements Game, Servant {

  private Player playerInTurn = Player.RED;
  private String cityProduction = GameConstants.LEGION;
  private Position actionPosition = new Position(1,1);

  @Override
  public Tile getTileAt(Position p) {
    return null;
  }

  @Override
  public Unit getUnitAt(Position p) {
    return null;
  }

  @Override
  public City getCityAt(Position p) {
    return null;
  }

  @Override
  public Player getPlayerInTurn() {
    return playerInTurn;
  }

  @Override
  public Player getWinner() {
    return Player.RED;
  }

  @Override
  public int getAge() {
    return 42;
  }

  @Override
  public boolean moveUnit(Position from, Position to) {
    return true;
  }

  @Override
  public void endOfTurn() {
    playerInTurn = (playerInTurn == Player.RED) ? Player.BLUE : Player.RED;
  }

  @Override
  public void changeWorkForceFocusInCityAt(Position p, String balance) {

  }

  @Override
  public void changeProductionInCityAt(Position p, String unitType) {
    cityProduction = unitType;
  }

  @Override
  public void performUnitActionAt(Position p) {
    actionPosition = p;
  }

  @Override
  public void addObserver(GameObserver observer) {

  }

  @Override
  public void setTileFocus(Position position) {

  }
}
