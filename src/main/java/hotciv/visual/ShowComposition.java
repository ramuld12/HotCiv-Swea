package hotciv.visual;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.SemiCivFactory;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.event.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.44.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowComposition {
  
  public static void main(String[] args) {
    Game game = new StubGame2();
    game = new GameImpl(new SemiCivFactory());

    DrawingEditor editor = 
      new MiniDrawApplication( "Click and/or drag any item to see all game actions",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click and drag any item to see Game's proper response.");
    editor.setTool( new CompositionTool(game,new SelectionTool(editor)) );
  }
}

class CompositionTool extends NullTool {
  private Tool alternatingTool;
  private SelectionTool tool;
  private Game game;

  public CompositionTool(Game game, SelectionTool tool) {
    this.game = game;
    this.tool = tool;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    Position positionPressed = (GfxConstants.getPositionFromXY(x,y));
    alternatingTool = new setFocusTool(game,tool);

    if (x > 559 && x < 590 && y > 64 && y < 110){
      alternatingTool = new EndOfTurnTool(game,tool);
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
