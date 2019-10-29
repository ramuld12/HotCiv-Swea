package hotciv.standard.decorators;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.AlphaCivFactory;
import hotciv.standard.HotCivFactory.HotCivFactory;

public class transcriptDecorator implements Game {
  private Game game;
  private Game decorateeGame;
  public transcriptDecorator (Game game) {
    this.game = game;
    decorateeGame = game;
  }

  public void changeLoggingState () {
    if (game == decorateeGame) {
      decorateeGame = game;
      game = new transcriptDecorator(game);
    } else {
      game = decorateeGame;
    }
  }

  @Override
  public boolean moveUnit(Position from, Position to) {
    System.out.println(game.getPlayerInTurn() + " moves " + game.getUnitAt(from) + " at position " + from +
            " to position " + to);
    return game.moveUnit(from, to);
  }

  //Game game2 = new transcriptDecorator(new GameImpl(new AlphaCivFactory()));



  @Override
  public void changeWorkForceFocusInCityAt(Position p, String balance) {
    //not implmented
  }

  @Override
  public void changeProductionInCityAt(Position p, String unitType) {
    System.out.println(game.getPlayerInTurn() + " changes production in city at " + p + " to " + unitType);
    game.changeProductionInCityAt(p, unitType);

  }

  @Override
  public void performUnitActionAt(Position p) {
    System.out.println(game.getPlayerInTurn() + " performs unit action with " + game.getUnitAt(p) + " at position " + p );
    game.performUnitActionAt(p);
  }

  @Override
  public Tile getTileAt(Position p) {
    return null;
  }

  @Override
  public Unit getUnitAt(Position p) {
    return null;
  }

  @Override
  public City getCityAt(Position p) {
    return null;
  }

  @Override
  public Player getPlayerInTurn() {
    return null;
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public int getAge() {
    return 0;
  }

  @Override
  public void endOfTurn() {

  }
}
