package hotciv.visual;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.SemiCivFactory;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

import java.awt.event.MouseEvent;

public class ShowRefesh {

    public static void main(String[] args) {
      Game game = new GameImpl(new SemiCivFactory());

      DrawingEditor editor =
              new MiniDrawApplication( "Click on refresh to refresh",
                      new HotCivFactory4(game) );
      editor.open();
      editor.showStatus("Click to see the game refresh");
      editor.setTool( new hotciv.visual.EndOfTurnTool(game, new SelectionTool(editor)) );
    }
  }
  class RefreshTool extends NullTool {
    private SelectionTool tool;
    private Game game;
    private boolean pressedOnRefresh = false;

    public RefreshTool(Game game, SelectionTool tool) {
      this.game = game;
      this.tool = tool;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
      if (x > 559 && x < 590 && y > 64 && y < 110) {
        pressedOnRefresh = true;
      }
      tool.mouseDown(e,x,y);
    }

    public void mouseUp(MouseEvent e, int x, int y) {
      if (x > 559 && x < 590 && y > 64 && y < 110
              && pressedOnRefresh) {
      }
      pressedOnRefresh = false;
      tool.mouseUp(e,x,y);
    }
  }
