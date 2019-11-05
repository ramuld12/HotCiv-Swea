package hotciv.visual;

import hotciv.tools.UnitMoveTool;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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

    DrawingEditor editor = 
      new MiniDrawApplication( "Click and/or drag any item to see all game actions",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click and drag any item to see Game's proper response.");

    // TODO: Replace the setting of the tool with your CompositionTool implementation.
    editor.setTool( new NullTool() );
  }
}

class compositionTool extends NullTool {
  private Tool alternatingTool;
  private SelectionTool tool;
  private Game game;

  public compositionTool(Game game, SelectionTool tool) {
    this.game = game;
    this.tool = tool;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    Point turnIcon = new Point(GfxConstants.TURN_SHIELD_X,GfxConstants.TURN_SHIELD_Y);
    Position positionPressed = (GfxConstants.getPositionFromXY(x,y));
    alternatingTool = new setFocusTool(game,tool);

    Rectangle iconInFocus = new Rectangle(e.getPoint(),new Dimension(50,50));
    if (iconInFocus.contains(turnIcon)){
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
