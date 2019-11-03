package hotciv.standard.TestStubs;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.view.CivDrawing;

public class GameObserverImplTest implements GameObserver {
  private ArrayList listTurnEnds;

  @Override
  public void worldChangedAt(Position pos) {

  }

  @Override
  public void turnEnds(Player nextPlayer, int age) {
    listTurnEnds.add(nextPlayer, age);
  }

  @Override
  public void tileFocusChangedAt(Position position) {

  }
}
