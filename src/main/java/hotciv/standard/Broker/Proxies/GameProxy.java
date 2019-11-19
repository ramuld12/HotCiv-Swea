package hotciv.standard.Broker.Proxies;

import frds.broker.Requestor;
import frds.broker.ClientProxy;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerConstants;

public class GameProxy implements Game, ClientProxy {
  private Requestor requestor;

  public GameProxy(Requestor requestor) {
    this.requestor = requestor;
  }

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
    return requestor.sendRequestAndAwaitReply(BrokerConstants.gameId,BrokerConstants.getPlayerInTurnString,Player.class);
  }

  @Override
  public Player getWinner() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.gameId,BrokerConstants.getWinnerString,Player.class);
  }

  @Override
  public int getAge() {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.gameId,BrokerConstants.getAgeString,Integer.class);
  }

  @Override
  public boolean moveUnit(Position from, Position to) {
    return requestor.sendRequestAndAwaitReply(BrokerConstants.gameId, BrokerConstants.moveUnit_action, Boolean.class, from, to);
  }

  @Override
  public void endOfTurn() {
    requestor.sendRequestAndAwaitReply(BrokerConstants.gameId, BrokerConstants.endTurn_action, void.class);
  }

  @Override //
  public void changeWorkForceFocusInCityAt(Position p, String balance) {
    //Never used
  }

  @Override
  public void changeProductionInCityAt(Position p, String unitType) {
    requestor.sendRequestAndAwaitReply(BrokerConstants.gameId, BrokerConstants.changeCityProduction, void.class, p, unitType);
  }

  @Override//
  public void performUnitActionAt(Position p) {

  }

  @Override
  public void addObserver(GameObserver observer) {

  }

  @Override
  public void setTileFocus(Position position) {

  }
}
