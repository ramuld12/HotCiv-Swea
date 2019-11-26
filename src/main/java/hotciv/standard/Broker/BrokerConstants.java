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
  public static final String getAgeString = GAME_PREFIX + "getAge";
  public static final String getPlayerInTurnString = GAME_PREFIX + "getPlayerInTurn";
  public static final String getWinnerString = GAME_PREFIX + "getWinner";
  public static final String moveUnit_action = GAME_PREFIX + "moveUnit";
  public static final String changeCityProduction = GAME_PREFIX + "changeProductionInCityAt";
  public static final String endTurn_action = GAME_PREFIX + "endOfTurn";
  public static final String unitAction = GAME_PREFIX + "performUnitActionAt";
  public static final String GAME_GET_CITY_METHOD = GAME_PREFIX + "getCityAt";
  public static final String GAME_GET_UNIT_METHOD = GAME_PREFIX + "getUnitAt";
  public static final String GAME_GET_TILE_METHOD = GAME_PREFIX + "getTileAt";

  // Methods for City
  public static final String getOwnerString = CITY_PREFIX + "getOwner";
  public static final String getSizeString = CITY_PREFIX + "getSize";
  public static final String getTreasuryString = CITY_PREFIX + "getTreasury";
  public static final String getProductionString = CITY_PREFIX + "getProduction";
  public static final String getWorkforceFocus = CITY_PREFIX + "getWorkforceFocus";

  //Methods for Tile
  public static final String tileString = TILE_PREFIX + "getTileString";

  // Methods for unit
  public static final String getTypeStringString = UNIT_PREFIX + "getTypeStringString";
  public static final String getMoveCountString = UNIT_PREFIX + "getMoveCount";
  public static final String getDefensiveStrengthString = UNIT_PREFIX + "getDefensiveStrength";
  public static final String getAttackingStrengthString = UNIT_PREFIX + "getAttackingStrength";
  public static final String isUnitMoveable = UNIT_PREFIX + "isMoveable";

  //Server
  public static final int serverPort = 37123;
}
