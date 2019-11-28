package hotciv.standard.Broker;

public class BrokerConstants {

  //Prefix
  public static final String GAME_PREFIX = "Game";
  public static final String UNIT_PREFIX = "Unit";
  public static final String TILE_PREFIX = "Tile";
  public static final String CITY_PREFIX = "City";

  //Methods for Game
  public static final String getAgeString = GAME_PREFIX + "_getAge";
  public static final String getPlayerInTurnString = GAME_PREFIX + "_getPlayerInTurn";
  public static final String getWinnerString = GAME_PREFIX + "_getWinner";
  public static final String moveUnit_action = GAME_PREFIX + "_moveUnit";
  public static final String changeCityProduction = GAME_PREFIX + "_changeProductionInCityAt";
  public static final String endTurn_action = GAME_PREFIX + "_endOfTurn";
  public static final String unitAction = GAME_PREFIX + "_performUnitActionAt";
  public static final String GAME_GET_CITY_METHOD = GAME_PREFIX + "_getCityAt";
  public static final String GAME_GET_UNIT_METHOD = GAME_PREFIX + "_getUnitAt";
  public static final String GAME_GET_TILE_METHOD = GAME_PREFIX + "_getTileAt";

  // Methods for City
  public static final String getOwnerCity = CITY_PREFIX + "_getOwner";
  public static final String getSizeString = CITY_PREFIX + "_getSize";
  public static final String getTreasuryString = CITY_PREFIX + "_getTreasury";
  public static final String getProductionString = CITY_PREFIX + "_getProduction";
  public static final String getWorkforceFocus = CITY_PREFIX + "_getWorkforceFocus";

  //Methods for Tile
  public static final String tileString = TILE_PREFIX + "_getTileString";

  // Methods for unit
  public static final String getOwnerUnit = UNIT_PREFIX + "_getOwner";
  public static final String getTypeString = UNIT_PREFIX + "_getTypeString";
  public static final String getMoveCountString = UNIT_PREFIX + "_getMoveCount";
  public static final String getDefensiveStrengthString = UNIT_PREFIX + "_getDefensiveStrength";
  public static final String getAttackingStrengthString = UNIT_PREFIX + "_getAttackingStrength";
  public static final String isUnitMoveable = UNIT_PREFIX + "_isMoveable";

  //Server
  public static final int serverPort = 37123;
}
