package hotciv.visual;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.SemiCivFactory;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.event.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/**
 * Template code for exercise FRS 36.39.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 * <p>
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com/
 */
public class ShowMove {

  public static void main(String[] args) {
    Game game = new StubGame2();
    game = new GameImpl(new SemiCivFactory());

    DrawingEditor editor =
            new MiniDrawApplication("Move any unit using the mouse",
                    new HotCivFactory4(game));
    editor.open();
    editor.showStatus("Move units to see Game's moveUnit method being called.");
    editor.setTool(new UnitMoveTool(game, new SelectionTool(editor), editor));
  }
}

class UnitMoveTool extends NullTool {
  private final Drawing currentDrawing;
  private SelectionTool tool;
  private final Game game;

  private Unit selectedUnit;
  private Position from;
  private Position to;


  public UnitMoveTool(Game game, SelectionTool tool, DrawingEditor editor) {
    this.game = game;
    this.tool = tool;
    currentDrawing = editor.drawing();
  }


  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    from = GfxConstants.getPositionFromXY(x, y);
    selectedUnit = game.getUnitAt(GfxConstants.getPositionFromXY(x, y));
    boolean isThereAUnit = selectedUnit != null;
    boolean isUnitOwnedByPlayerInTurn = isThereAUnit && selectedUnit.getOwner() == game.getPlayerInTurn();
    if (isThereAUnit && isUnitOwnedByPlayerInTurn) {
      tool.mouseDown(e, x, y);
    }
  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y) {
    Position position = GfxConstants.getPositionFromXY(x, y);
    boolean isThereAUnit = selectedUnit != null;
    boolean isXWithinWorldBounds = GfxConstants.getPositionFromXY(x, y).getColumn() <= GameConstants.WORLDSIZE && 0 <= GfxConstants.getPositionFromXY(x, y).getColumn();
    boolean isYWithinWorldBounds = GfxConstants.getPositionFromXY(x, y).getRow() <= GameConstants.WORLDSIZE && 0 <= GfxConstants.getPositionFromXY(x, y).getRow();
    if (isThereAUnit &&
            isXWithinWorldBounds &&
            isYWithinWorldBounds) {
      tool.mouseDrag(e, x, y);
    }
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    to = GfxConstants.getPositionFromXY(x, y);
    boolean isXWithinWorldBounds = GfxConstants.getPositionFromXY(x, y).getColumn() < GameConstants.WORLDSIZE && 0 <= GfxConstants.getPositionFromXY(x, y).getColumn();
    boolean isYWithinWorldBounds = GfxConstants.getPositionFromXY(x, y).getRow() < GameConstants.WORLDSIZE && 0 <= GfxConstants.getPositionFromXY(x, y).getRow();
    //boolean isDistanceDraggedAllowed = game.moveUnit(from, to);
    if (isXWithinWorldBounds && isYWithinWorldBounds) {
      game.moveUnit(from, to);
      game.setTileFocus(to);
    }
    currentDrawing.requestUpdate();
    tool.mouseUp(e, x, y);

  }
}