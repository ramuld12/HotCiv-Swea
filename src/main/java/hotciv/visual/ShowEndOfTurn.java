package hotciv.visual;

import hotciv.standard.GameImpl;
import hotciv.standard.HotCivFactory.SemiCivFactory;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.42.

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
public class ShowEndOfTurn {
  
  public static void main(String[] args) {
    //Game game = new StubGame2();
    Game game = new GameImpl(new SemiCivFactory());

    DrawingEditor editor = 
      new MiniDrawApplication( "Click top shield to end the turn",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click to shield to see Game's endOfTurn method being called.");
    editor.setTool( new EndOfTurnTool(game, new SelectionTool(editor)) );
  }
}
class EndOfTurnTool extends NullTool {
  private SelectionTool tool;
  private Game game;
  private boolean pressedOnShield = false;

  public EndOfTurnTool(Game game, SelectionTool tool) {
    this.game = game;
    this.tool = tool;
  }

  public void mouseDown(MouseEvent e, int x, int y) {
    if (x > 559 && x < 590 && y > 64 && y < 110) {
      pressedOnShield = true;
    }
    tool.mouseDown(e,x,y);
  }

  public void mouseUp(MouseEvent e, int x, int y) {
    if (x > 559 && x < 590 && y > 64 && y < 110
            && pressedOnShield) {
      game.endOfTurn();
    }
    pressedOnShield = false;
    tool.mouseUp(e,x,y);
  }
}
