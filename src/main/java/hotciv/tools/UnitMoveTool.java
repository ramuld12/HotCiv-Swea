package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.UnitImpl;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;
import minidraw.standard.handlers.DragTracker;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class UnitMoveTool extends NullTool {

  private DrawingEditor editor;
  private SelectionTool tool;
  private final Game game;

  public Unit selectedUnit;
  private UnitFigure unitFigure;

  /**
   * the figure that is being dragged. If null then its operation is not that of
   * dragging a figure (or a set of figures)
   */
  protected Figure draggedFigure;

  public UnitMoveTool(Game game, SelectionTool tool) {
    this.game = game;
    this.tool = tool;
  }


  @Override
  public void mouseDown(MouseEvent e, int x, int y) {

    selectedUnit = game.getUnitAt(GfxConstants.getPositionFromXY(x,y));
    boolean isThereAUnit = selectedUnit != null;
    boolean isUnitOwnedByPlayerInTurn = selectedUnit.getOwner() == game.getPlayerInTurn();
    if (!isThereAUnit && isUnitOwnedByPlayerInTurn){
      tool.mouseDown(e,x,y);
    }


  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y) {

    boolean isThereAUnit = selectedUnit != null;
    boolean isUnitOwnedByPlayerInTurn = selectedUnit.getOwner() == game.getPlayerInTurn();
    if (!isThereAUnit && isUnitOwnedByPlayerInTurn){
      tool.mouseDrag(e,x,y);
    }
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) { ;
    Position dropUnitPosition = new Position(x,y);

   boolean isXWithinWorldBounds = x <= GameConstants.WORLDSIZE && 0 < x;
   boolean isYWithinWorldBounds = 0 < y && y <= GameConstants.WORLDSIZE;

  }

  @Override
  public void mouseMove(MouseEvent e, int x, int y) {

  }

}
