package hotciv.standard.Broker;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

public interface NameService {

  void putTile(String objectID, Game game);

  void putUnit(String objectID, Game game);

  void putCity(String objectID, Game game);

  Tile getTile(String objectID);

  Unit getUnit(String objectID);

  City getCity(String objectID);


}
