package hotciv.visual;

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

        DrawingEditor editor = new MiniDrawApplication( "Click and/or drag any item to see all game actions", new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");

        // TODO: Replace the setting of the tool with your CompositionTool implementation.
        editor.setTool( new CompositeTool(editor, game) );
    }
}

class CompositeTool extends NullTool {
    private MoveTool moveTool;
    private FocusTool focusTool;
    private EndTurnTool endTurnTool;
    private ActionTool actionTool;

    private Game game;
    private DrawingEditor editor;
    boolean selectedUnit = false;
    Position selectedPos;
    public CompositeTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
        moveTool = new MoveTool(editor, game);
        focusTool = new FocusTool(editor, game);
        endTurnTool = new EndTurnTool(editor, game);
        actionTool = new ActionTool(editor, game);
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        Position currentPos = GfxConstants.getPositionFromXY(x, y);
        if (GfxConstants.TURN_SHIELD_X < x && x < GfxConstants.TURN_SHIELD_X + 27
                && GfxConstants.TURN_SHIELD_Y < y && y < GfxConstants.TURN_SHIELD_Y + 39) {
            endTurnTool.mouseDown(e, x, y);
            selectedUnit = false;
        }

        if (!selectedUnit) {
            focusTool.mouseDown(e, x, y);
            selectedUnit = game.getUnitAt(currentPos) != null;
        }
        else {
            // if you click the same unit twice, use its action
            if (selectedPos == currentPos)
                actionTool.mouseDown(e, x, y);

            // if you have selected a unit and click anywhere in its movement range, move it
            if (Math.abs(selectedPos.getRow() - currentPos.getRow()) <= game.getUnitAt(selectedPos).getMoveCount()
                    && Math.abs(selectedPos.getColumn() - currentPos.getColumn()) <= game.getUnitAt(selectedPos).getMoveCount()) {
                game.moveUnit(selectedPos, currentPos);
                selectedUnit = false; // deselect the unit
            }

            // else focus whatever you clicked on
            else
                focusTool.mouseDown(e, x, y);


            // TODO: IMPLEMENT EVERYTHING ELSE
        }
        selectedPos = GfxConstants.getPositionFromXY(x, y);
    }
}
