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
      editor.setTool( new hotciv.visual.RefreshTool(game, new SelectionTool(editor), editor) );
    }
  }
  class RefreshTool extends NullTool {
    private DrawingEditor editor;
    private SelectionTool tool;
    private boolean pressedOnRefresh = false;

    public RefreshTool(Game game, SelectionTool tool, DrawingEditor editor) {
      this.tool = tool;
      this.editor = editor;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
      if (x > 510 && x < 590 && y > 472 && y < 492) {
        pressedOnRefresh = true;
      }
      tool.mouseDown(e,x,y);
    }

    public void mouseUp(MouseEvent e, int x, int y) {
      if (x > 510 && x < 590 && y > 472 && y < 492
              && pressedOnRefresh) {
        editor.drawing().requestUpdate();
      }
      pressedOnRefresh = false;
      tool.mouseUp(e,x,y);
    }
  }
