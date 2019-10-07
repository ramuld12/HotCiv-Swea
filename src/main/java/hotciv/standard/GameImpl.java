package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.HotCivFactory.*;
import hotciv.standard.strategies.AgingStrategies.AgingStrategy;
import hotciv.standard.strategies.BattleStrategies.BattleStrategy;
import hotciv.standard.strategies.UnitActionStrategies.UnitActionStrategy;
import hotciv.standard.strategies.WinningStrategies.WinningStrategy;
import hotciv.standard.strategies.WorldLayoutStrategies.WorldLayoutStrategy;
import hotciv.utility.*;

import java.util.HashMap;
import java.util.*;

/**
 * Skeleton implementation of HotCiv.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class GameImpl implements Game {
  private WinningStrategy winningStrategy;
  private AgingStrategy agingStrategy;
  private UnitActionStrategy unitActionStrategy;
  private WorldLayoutStrategy worldLayoutStrategy;
  private BattleStrategy battleStrategy;
  private HotCivFactory concreteFactory;
  private Player playerInTurn = Player.RED; //The current player in turn, initially set to red
  private int gameAge = -4000; //The current age of the game, initially set to -4000
  private HashMap<Position, TileImpl> world; //HashMap for representing the different tiletypes
  private HashMap<Position, CityImpl> cities; //HashMap representing the cities
  private HashMap<Position, UnitImpl> units; //HashMap representing the units
  private HashMap<Player, Integer> playerVictories; //HashMap representing playervictories
  private int roundNumber;


  /**
   * Constructor method for GameImpl, which can create a GameImpl for Alpha-, Beta-, Delta-
   * and GammaCiv depending on strategies given.
   * Initializes the private variables with tiletypes, city initial positions for the cities
   * and initial unit placements in the world.
   */
  public GameImpl(HotCivFactory concreteFactory) {
    this.agingStrategy = concreteFactory.createAgingStrategy();
    this.winningStrategy = concreteFactory.createWinningStrategy();
    this.unitActionStrategy = concreteFactory.createUnitActionStrategy();
    this.worldLayoutStrategy = concreteFactory.createWorldLayoutStrategy();
    this.battleStrategy = concreteFactory.createBattleStrategy();
    world = new HashMap<>();
    cities = new HashMap<>();
    units = new HashMap<>();
    playerVictories = new HashMap<>();
    worldLayoutStrategy.createTheWorld(this);
    winningStrategy.initializePlayerVictories(this);
  }

  // === Accessor methods ======================================

  public HashMap<Position, TileImpl> getWorld() {
    return world;
  }

  public HashMap<Position, CityImpl> getCities() {
    return cities;
  }

  public HashMap<Position, UnitImpl> getUnits() {
    return units;
  }

  public HashMap<Player, Integer> getPlayers() {
    return playerVictories;
  }

  public TileImpl getTileAt(Position p) {
    return world.get(p);
  }

  public UnitImpl getUnitAt(Position p) {
    return units.get(p);
  }

  public CityImpl getCityAt(Position p) {
    return cities.get(p);
  }

  public Player getPlayerInTurn() {
    return playerInTurn;
  }

  public int getAge() {
    return gameAge;
  }

  public int getRoundNumber() {
    return roundNumber;
  }

  public int getVictoriesForPlayer(Player p) {
    return playerVictories.get(p);
  }

  public Player getWinner() {
    return winningStrategy.getWinner(this);
  }

  public WinningStrategy getWinningStrategy() { return winningStrategy; }

  // === Setter methods ======================================

  /**
   * Sets the game age to a given age
   *
   * @param newGameAge the age the game will now have
   */
  public void setGameAge(int newGameAge) {
    gameAge = newGameAge;
  }

  public void setRoundNumber (int newRoundNumber) {roundNumber = newRoundNumber;}

  public void changeProductionInCityAt(Position p, String unitType) {
    cities.get(p).changeProduction(unitType);
  }

  /**
   * Creates a city at a given position. The owner of
   * the new city is set to the player in turn.
   * Precondition: There can not be a city at the position given
   *
   * @param position position of the new city
   */
  public void createCityAtPosition(Position position) {
    boolean isPositionVacantForCity = cities.get(position) == null;
    if (isPositionVacantForCity) {
      cities.put(position, new CityImpl(playerInTurn));
    }
  }

  /**
   * Creates a unit of a given type at position.
   * @param position position for the unit
   * @param unitType type of the new unit
   * @param owner the owner of the new unit
   */
  public void createUnitAtPosition(Position position, String unitType, Player owner) {
    units.put(position, new UnitImpl(unitType, owner));
  }

  public void createTileAtPosition(Position position, TileImpl tiletype) {
    world.put(position, tiletype);
  }

  public boolean moveUnit(Position from, Position to) {
    boolean isFromInTheWorld = world.containsKey(from);
    boolean isToInTheWorld = world.containsKey(to);
    boolean isTileTypeAtToValidForMovement = world.get(to).isValidMovementTileType(units.get(from));
    boolean isThereAUnitAtFrom = units.get(from) != null;
    boolean isThereAUnitAtTo = units.get(to) != null;
    boolean isUnitOwnedByPlayerInTurn = isThereAUnitAtFrom && units.get(from).getOwner() == playerInTurn;
    boolean isThereAnEnemyUnitAtTo = isThereAUnitAtFrom && isThereAUnitAtTo && units.get(from).getOwner() != units.get(to).getOwner();
    boolean isThereAFriendlyUnitAtTo = isThereAUnitAtFrom && isThereAUnitAtTo && units.get(from).getOwner() == units.get(to).getOwner();
    boolean isUnitMoveable = isThereAUnitAtFrom && units.get(from).isMoveable();
    boolean hasMovesLeft = isThereAUnitAtFrom && units.get(from).getMoveCount() > 0;
    boolean didDefenceWin = isThereAnEnemyUnitAtTo && !battleStrategy.handlingOfAttack(this, from, to);
    boolean isThereACityAtPositionTo = cities.containsKey(to);
    boolean isTheCityForeign = isThereACityAtPositionTo && cities.get(to).getOwner() != playerInTurn;

    if (!(isFromInTheWorld &&
            isToInTheWorld &&
            isTileTypeAtToValidForMovement &&
            isThereAUnitAtFrom &&
            isUnitOwnedByPlayerInTurn &&
            !isThereAFriendlyUnitAtTo &&
            isUnitMoveable &&
            hasMovesLeft &&
            !didDefenceWin)) {
      return false;
    }

    if (isThereACityAtPositionTo && isTheCityForeign) {
      cities.get(to).changeOwner(playerInTurn);
    }

    //Handling of movement for the unit
    movingTheUnit(from, to);
    boolean didMovingSucced = units.get(to) != null;
    return didMovingSucced;
  }
  
  public void movingTheUnit(Position from, Position to) {
    for (Position unitPosition : Utility.get8neighborhoodOf(from)) {
      boolean isTheCurrentNeighbourTo = unitPosition.equals(to);
      if (isTheCurrentNeighbourTo) {
        //Find the tile the unit is trying to move to,
        // create a new unit with moveCounter 0 of the same type there and remove the old
        String unitType = units.get(from).getTypeString();
        units.remove(from);
        createUnitAtPosition(to, unitType, playerInTurn);
        units.get(to).decreaseMoveCount();
      }
    }
  }

  public void increaseCitySize() {
    for (CityImpl city : cities.values()) {
      if (5 + city.getSize() * 3 > city.getFoodAmount()) {
        return;
      } else {
        city.incrementCitySize();
      }
    }
  }

  public void endOfTurn() {
    boolean isPlayerInTurnRed = playerInTurn.equals(Player.RED);
    if (isPlayerInTurnRed) {
      playerInTurn = Player.BLUE;
    } else {
      playerInTurn = Player.RED;
      gameAge += agingStrategy.getAgeStep(this);
      cities.values().forEach(CityImpl::increaseTreas);
      cities.values().forEach(CityImpl::incrementFood);
      units.values().forEach(UnitImpl::resetMoveCounter);
      cities.keySet().forEach(p -> produceUnitInCityAt(p, cities.get(p)));
      increaseCitySize();
      roundNumber ++;
      winningStrategy.changeStateIfNeeded(this);
    }
  }

  @Override
  public void changeWorkForceFocusInCityAt(Position p, String balance) {
 // not implemented yet
  }

  /**
   * Produces unit in a city. If the city already has a unit
   * in the city, it will produce a unit at a neighbour position
   * , first looking to the north then going clockwise
   *
   * @param cityPosition position of the city to produce unit
   * @param city         the city at cityPosition
   */
  public void produceUnitInCityAt(Position cityPosition, CityImpl city) {
    boolean isCityPositionVacantForUnit = units.get(cityPosition) == null;
    boolean doesCityHaveEnoughTreasure = city.hasEnoughTreasure();
    if (!doesCityHaveEnoughTreasure) {return;}
    city.reduceTreasury(city.getProdCost());
    if (isCityPositionVacantForUnit) {
      createUnitAtPosition(cityPosition, city.getProduction(), city.getOwner());
    } else {
      createUnitAtNeighbourPosition(cityPosition, city.getProduction(), city.getOwner());
    }
  }

  /**
   * Creates a unit at a neighbour position to the given position
   * @param centerPosition position for which neighbours are found
   * @param unitType type of the new unit
   * @param owner owner of new unit
   */
  private void createUnitAtNeighbourPosition(Position centerPosition, String unitType, Player owner) {
    Position vacantNeighbourPosition = findFirstVacantNeighbourPosition(centerPosition);
    createUnitAtPosition(vacantNeighbourPosition, unitType, owner);
  }

  /**
   * Find the first vacant position around a
   * given centerposition. Returns null if none
   * of the neighbour positions are vacant.
   *
   * @param centerPosition the center position
   * @return the first eligible neighbour position.
   */
  public Position findFirstVacantNeighbourPosition(Position centerPosition) {
    for (Position neighbourPosition : Utility.get8neighborhoodOf(centerPosition)) {
      boolean isNeighbourPositionVacantForUnit = units.get(neighbourPosition) == null;
      boolean isValidTileInWorld = world.get(neighbourPosition).isValidMovementTileType(units.get(neighbourPosition));

      if (!(isNeighbourPositionVacantForUnit &&
              isValidTileInWorld)) {
        continue;
      }
      return neighbourPosition;
    }
    return null;
  }

  public int findNumberOfFriendlyNeighbourUnits(Position centerPosition) {
    int numberOfFriends = 0;
    for (Position neighbourPosition : Utility.get8neighborhoodOf(centerPosition)) {
      boolean isThereAUnitAtNeighbourPosition = getUnitAt(neighbourPosition) != null;
      boolean isFriendlyUnit = isThereAUnitAtNeighbourPosition && getUnitAt(neighbourPosition).getOwner().equals(getUnitAt(centerPosition).getOwner());
      if (isFriendlyUnit) {
        numberOfFriends++;
      }
    }
    return numberOfFriends;
  }

    public void performUnitActionAt (Position p){
      unitActionStrategy.performUnitActionAt(this, p);
    }

    /**
     * Removes a unit at a certain position from the units map
     * Precondition: There has to be a unit at the given position
     * @param unitPosition the position of the unit to be removed
     */
    public void removeUnitFromUnitsMapAtPosition (Position unitPosition){
      boolean isThereAUnitAtPosition = units.get(unitPosition) != null;
      if (isThereAUnitAtPosition) {
        units.remove(unitPosition);
      }
    }

  public void removeCityFromCitiesMapAtPosition(Position cityPosition) {
      boolean isThereACityAtPosition = cities.get(cityPosition) != null;
      if (isThereACityAtPosition){
        cities.remove(cityPosition);
      }
  }

  public void removeTileFromWorldMapAtPosition(Position actionPosition) {
    world.remove(actionPosition);
  }

    // === Boolean methods ======================================

    /**
     *  Checks if the player currently in turn owns all citites
     * @return true if player in turn owns all the cities
     */
    public boolean doesPlayerInTurnOwnAllCities () {
      Set<Player> owners = new HashSet<>();
      cities.values().forEach(city -> owners.add(city.getOwner()));

      boolean doesAPlayerOwnAllCities = owners.size() == 1;
      return doesAPlayerOwnAllCities && owners.contains(playerInTurn);
    }
}
