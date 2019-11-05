package hotciv.tools;

import hotciv.framework.Game;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;
import minidraw.standard.SelectionTool;
import minidraw.standard.handlers.DragTracker;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class UnitMoveTool implements Tool {

  private DrawingEditor editor;
  private final Game game;

  /**
   * the figure that is being dragged. If null then its operation is not that of
   * dragging a figure (or a set of figures)
   */
  protected Figure draggedFigure;

  public UnitMoveTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;
  }


  @Override
  public void mouseDown(MouseEvent e, int x, int y) {

  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y) {

  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {

  }

  @Override
  public void mouseMove(MouseEvent e, int x, int y) {

  }

  @Override
  public void keyDown(KeyEvent e, int key) {

  }
}
