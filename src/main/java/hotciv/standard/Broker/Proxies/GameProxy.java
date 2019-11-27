package hotciv.standard.Broker.Proxies;

import frds.broker.Requestor;
import frds.broker.ClientProxy;
import hotciv.framework.*;
import hotciv.standard.Broker.BrokerConstants;

public class GameProxy implements Game, ClientProxy {
  private final String objectId;
  private String gameProxyUUID;
  private Requestor requestor;
  private GameObserver gameObserver;

  public GameProxy(String objectId, Requestor requestor) {
    this.requestor = requestor;
    this.gameProxyUUID = java.util.UUID.randomUUID().toString();
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
            "none", BrokerConstants.GAME_GET_CITY_METHOD, String.class, p);
    return new CityProxy(id, requestor);
  }

  @Override
  public Player getPlayerInTurn() {
    return requestor.sendRequestAndAwaitReply(this.gameProxyUUID,BrokerConstants.getPlayerInTurnString,Player.class);
  }

  @Override
  public Player getWinner() {
    return requestor.sendRequestAndAwaitReply(this.gameProxyUUID,BrokerConstants.getWinnerString,Player.class);
  }

  @Override
  public int getAge() {
    return requestor.sendRequestAndAwaitReply(this.gameProxyUUID,BrokerConstants.getAgeString,Integer.class);
  }

  @Override
  public boolean moveUnit(Position from, Position to) {
    boolean reply = requestor.sendRequestAndAwaitReply(this.gameProxyUUID, BrokerConstants.moveUnit_action, Boolean.class, from, to);
    gameObserver.worldChangedAt(from);
    gameObserver.worldChangedAt(to);
    return reply;
  }

  @Override
  public void endOfTurn() {
    requestor.sendRequestAndAwaitReply(this.gameProxyUUID, BrokerConstants.endTurn_action, void.class);
    gameObserver.turnEnds(getPlayerInTurn(),getAge());
  }

  @Override //
  public void changeWorkForceFocusInCityAt(Position p, String balance) {
    //Never used
  }

  @Override
  public void changeProductionInCityAt(Position p, String unitType) {
    requestor.sendRequestAndAwaitReply(this.gameProxyUUID, BrokerConstants.changeCityProduction, void.class, p, unitType);
    gameObserver.worldChangedAt(p);
  }

  @Override//
  public void performUnitActionAt(Position p) {
    requestor.sendRequestAndAwaitReply(this.gameProxyUUID, BrokerConstants.unitAction, void.class, p);
    gameObserver.worldChangedAt(p);
  }

  @Override
  public void addObserver(GameObserver observer) {
    this.gameObserver = observer;
  }

  @Override
  public void setTileFocus(Position position) {
    requestor.sendRequestAndAwaitReply(this.gameProxyUUID,BrokerConstants.tileFocusString,String.class, position);
    gameObserver.worldChangedAt(position);
  }

  public String getGameProxyUUID(){
    return gameProxyUUID;
  }
}
