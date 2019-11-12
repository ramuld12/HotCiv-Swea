package hotciv.visual;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.SemiCivFactory;
import hotciv.stub.StubGame2;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

public class ShowSemi {
  public static void main(String[] args) {
    Game game = new GameImpl(new SemiCivFactory());

    DrawingEditor editor =
            new MiniDrawApplication( "SemiCiv",
                    new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Playable SemiCiv");
    editor.setTool( new CompositionTool(game,new SelectionTool(editor)) );
  }
}
