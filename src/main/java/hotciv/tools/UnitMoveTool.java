package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
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
  private Position from;
  private Position to;


  public UnitMoveTool(Game game, SelectionTool tool) {
    this.game = game;
    this.tool = tool;
  }


  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    from = GfxConstants.getPositionFromXY(x,y);
    selectedUnit = game.getUnitAt(GfxConstants.getPositionFromXY(x,y));
    boolean isThereAUnit = selectedUnit != null;
    boolean isUnitOwnedByPlayerInTurn = isThereAUnit && selectedUnit.getOwner() == game.getPlayerInTurn();
    if (isThereAUnit && isUnitOwnedByPlayerInTurn){
      tool.mouseDown(e,x,y);
    }


  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y) {
    Position position = GfxConstants.getPositionFromXY(x,y);
    boolean isXWithinWorldBounds = GfxConstants.getPositionFromXY(x,y).getColumn() <= GameConstants.WORLDSIZE && 0 <= GfxConstants.getPositionFromXY(x,y).getColumn();
    boolean isYWithinWorldBounds = 0 <= GfxConstants.getPositionFromXY(x,y).getRow() && GfxConstants.getPositionFromXY(x,y).getRow() <= GameConstants.WORLDSIZE;
      if (selectedUnit != null && isXWithinWorldBounds && isYWithinWorldBounds) {
        tool.mouseDrag(e,GfxConstants.getXFromColumn(position.getColumn()),GfxConstants.getYFromRow(position.getRow()));
      }
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {

    to = GfxConstants.getPositionFromXY(x,y);
   boolean isXWithinWorldBounds = GfxConstants.getPositionFromXY(x,y).getColumn() <= GameConstants.WORLDSIZE && 0 <= GfxConstants.getPositionFromXY(x,y).getColumn();
   boolean isYWithinWorldBounds = 0 <= GfxConstants.getPositionFromXY(x,y).getRow() && GfxConstants.getPositionFromXY(x,y).getRow() <= GameConstants.WORLDSIZE;
   if (isXWithinWorldBounds && isYWithinWorldBounds){
     game.moveUnit(from,to);
     game.setTileFocus(to);
   }
    tool.mouseUp(e,x,y);
  }

}
