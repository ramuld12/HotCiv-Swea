package hotciv.standard.strategies.WorldLayoutStrategies;

import hotciv.framework.*;
import hotciv.standard.*;

import java.util.HashMap;

public class AlphaCivWorldLayoutStrategy implements WorldLayoutStrategy {
  @Override
  public void createTheWorld(GameImpl game) {


    //Initialize the gameboard
    HashMap<Position, TileImpl> world = game.getWorld();
    for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
      for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
        world.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
      }
    }
    world.put(new Position(1, 0), new TileImpl(GameConstants.OCEANS));
    world.put(new Position(0, 1), new TileImpl(GameConstants.HILLS));
    world.put(new Position(2, 2), new TileImpl(GameConstants.MOUNTAINS));

    //Initialize the cities map
    HashMap<Position, CityImpl> cities = game.getCities();
    cities.put(new Position(1, 1), new CityImpl(Player.RED));
    cities.put(new Position(4, 1), new CityImpl(Player.BLUE));

    //Initialize the units map
    HashMap<Position, UnitImpl> units = game.getUnits();
    units.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
    units.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    units.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED));
  }
}
