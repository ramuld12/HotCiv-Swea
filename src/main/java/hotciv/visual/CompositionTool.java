package hotciv.visual;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

import java.awt.event.MouseEvent;

public class CompositionTool extends NullTool {
  private DrawingEditor editor;
  private Tool alternatingTool;
  private SelectionTool tool;
  private Game game;

  public CompositionTool(Game game, SelectionTool tool, DrawingEditor editor) {
    this.game = game;
    this.tool = tool;
    this.editor = editor;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    Position positionPressed = (GfxConstants.getPositionFromXY(x,y));
    alternatingTool = new setFocusTool(game,tool);

    if (x > 559 && x < 590 && y > 64 && y < 110){
      alternatingTool = new EndOfTurnTool(game,tool);
    }
    else if(x > 510 && x < 590 && y > 472 && y < 492) {
      alternatingTool = new RefreshTool(game, tool, editor);
    }
    else if (game.getUnitAt(positionPressed) != null){
      if (e.isShiftDown()){
        alternatingTool = new actionTool(game,tool);
      } else {
        alternatingTool = new UnitMoveTool(game,tool);
      }

    }
    alternatingTool.mouseDown(e,x,y);
  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y){
    alternatingTool.mouseDrag(e,x,y);
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y){
    alternatingTool.mouseUp(e,x,y);
  }

}
