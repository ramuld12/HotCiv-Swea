package hotciv.standard.Broker;

import hotciv.framework.City;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

public interface NameService {

  void putTile(String objectID, Tile tile);

  void putUnit(String objectID, Unit unit);

  void putCity(String objectID, City city);

  Tile getTile(String objectID);

  Unit getUnit(String objectID);

  City getCity(String objectID);


}
