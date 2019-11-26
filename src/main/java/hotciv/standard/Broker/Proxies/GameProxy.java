package hotciv.standard.Broker.Proxies;

import frds.broker.Requestor;
import frds.broker.ClientProxy;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerConstants;

public class GameProxy implements Game, ClientProxy {
  private final String objectId;
  private Requestor requestor;

  public GameProxy(String objectId, Requestor requestor) {
    this.requestor = requestor;
    this.objectId = objectId;
  }

  @Override
  public Tile getTileAt(Position p) {
    String id = requestor.sendRequestAndAwaitReply(
            "none", BrokerConstants.GAME_GET_TILE_METHOD, String.class, p);
    return new TileProxy(id, requestor);
  }

  @Override
  public Unit getUnitAt(Position p) {
    String id = requestor.sendRequestAndAwaitReply(
            "none", BrokerConstants.GAME_GET_UNIT_METHOD, String.class, p);
    return new UnitProxy(id, requestor);
  }

  @Override
  public City getCityAt(Position p) {
    String id = requestor.sendRequestAndAwaitReply(
            "", BrokerConstants.GAME_GET_CITY_METHOD, String.class, p);
    return new CityProxy(id, requestor);
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
    requestor.sendRequestAndAwaitReply(BrokerConstants.gameId, BrokerConstants.unitAction, void.class, p);
  }

  @Override
  public void addObserver(GameObserver observer) {

  }

  @Override
  public void setTileFocus(Position position) {

  }
}
