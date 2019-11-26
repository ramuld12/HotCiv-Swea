package hotciv.standard.Broker;

import hotciv.framework.*;
import hotciv.stub.CityStub;
import java.util.HashMap;
import java.util.UUID;

public class NameServiceImpl implements NameService {
  private HashMap<String, Tile> world = new HashMap<>(); //HashMap for representing the different tiletypes
  private HashMap<String, City> cities = new HashMap<>();; //HashMap representing the cities
  private HashMap<String, Unit> units = new HashMap<>();; //HashMap representing the units


  @Override
  public void putTile(String objectID, Tile tile) {

  }

  @Override
  public void putUnit(String objectID, Unit unit) {

  }

  @Override
  public void putCity(String objectID, City city) {
    cities.put(objectID, city);
  }

  @Override
  public Tile getTile(String objectID) {
    return world.get(objectID);
  }

  @Override
  public Unit getUnit(String objectID) {
    return units.get(objectID);
  }

  @Override
  public City getCity(String objectID) {
    return cities.get(objectID);
  }
}