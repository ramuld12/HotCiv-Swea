package hotciv.tools;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.standard.AbstractTool;
import minidraw.standard.SelectionTool;

public class UnitMoveTool extends AbstractTool {


  private final Game game;

  public UnitMoveTool(DrawingEditor editor, Game game) {
    super(editor);
    this.game = game;
  }


}
