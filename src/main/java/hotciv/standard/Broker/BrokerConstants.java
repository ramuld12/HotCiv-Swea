package hotciv.standard.Broker;

public class BrokerConstants {

  //Prefix
  public static final String GAME_PREFIX = "Game";
  public static final String UNIT_PREFIX = "Unit";
  public static final String TILE_PREFIX = "Tile";
  public static final String CITY_PREFIX = "City";

  // Values
  public static final int ok_status = 200;
  public static final String gameId = "3";
  public static final String cityId = "4";
  public static final String tileId = "57";
  public static final String unitId = "55";

  public static final String notOk_status = "DillerDaller";


  //Methods for Game
  public static final String getAgeString = "getAge";
  public static final String getPlayerInTurnString = "getPlayerInTurn";
  public static final String getWinnerString = "getWinner";
  public static final String moveUnit_action = "moveUnit";
  public static final String changeCityProduction = "changeProductionInCityAt";
  public static final String endTurn_action = "endOfTurn";
  public static final String unitAction = "performUnitActionAt";

  // Methods for City
  public static final String getOwnerString = CITY_PREFIX + "getOwner";
  public static final String getSizeString = "getSize";
  public static final String getTreasuryString = "getTreasury";
  public static final String getProductionString = "getProduction";
  public static final String getWorkforceFocus = "getWorkforceFocus";

  //Methods for Tile
  public static final String tileString = "getTileString";

  // Methods for unit
  public static final String getTypeStringString = "getTypeStringString";
  public static final String getMoveCountString = "getMoveCount";
  public static final String getDefensiveStrengthString = "getDefensiveStrength";
  public static final String getAttackingStrengthString = "getAttackingStrength";
  public static final String isUnitMoveable = "isMoveable";

  //Server
  public static final int serverPort = 37123;

  //Object references
  public static final String GAME_GET_CITY_METHOD = GAME_PREFIX + "getCityAt";
  public static final String GAME_GET_UNIT_METHOD = GAME_PREFIX + "getUnitAt";
  public static final String GAME_GET_TILE_METHOD = GAME_PREFIX + "getTileAt";
}
