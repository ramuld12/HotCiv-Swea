package hotciv.standard.TestStubs;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.view.CivDrawing;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class GameObserverImplTest implements GameObserver {
  private List<Player> endOfTurnPlayers = new ArrayList<>();
  private List<Integer> endOfTurnAges =  new ArrayList<>();
  private List<Position> worldChanges = new ArrayList<>();

  @Override
  public void worldChangedAt(Position pos) {
    worldChanges.add(pos);
  }

  @Override
  public void turnEnds(Player nextPlayer, int age) {
    endOfTurnPlayers.add(nextPlayer);
    endOfTurnAges.add(age);
  }

  @Override
  public void tileFocusChangedAt(Position position) {

  }

  public List<Player> getEndOfTurnPlayers() {
    return endOfTurnPlayers;
  }

  public List<Integer> getEndOfTurnAges() {
    return endOfTurnAges;
  }

  public List<Position> getWorldChanges() {
    return worldChanges;
  }

}
