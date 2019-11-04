package hotciv.tools;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;
import minidraw.standard.SelectionTool;
import minidraw.standard.handlers.DragTracker;

public class UnitMoveTool extends AbstractTool implements Tool {


  private final Game game;

  /**
   * the figure that is being dragged. If null then its operation is not that of
   * dragging a figure (or a set of figures)
   */
  protected Figure draggedFigure;

  public UnitMoveTool(DrawingEditor editor, Game game) {
    super(editor);
    this.game = game;
  }


}
