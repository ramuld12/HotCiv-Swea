package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
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

    selectedUnit = game.getUnitAt(GfxConstants.getPositionFromXY(x,y));
    boolean isThereAUnit = selectedUnit != null;
    boolean isUnitOwnedByPlayerInTurn = selectedUnit.getOwner() == game.getPlayerInTurn();
    if (!isThereAUnit && isUnitOwnedByPlayerInTurn){
      tool.mouseDrag(e,x,y);
  }
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    
  }

  @Override
  public void mouseMove(MouseEvent e, int x, int y) {

  }

}
