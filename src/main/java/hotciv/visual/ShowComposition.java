package hotciv.visual;

import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
        editor.setTool(new CompositeTool(editor, game) );
    }
}

class CompositeTool extends NullTool {
    private FocusTool focusTool;
    private EndTurnTool endTurnTool;
    private ActionTool actionTool;

    private Game game;
    private DrawingEditor editor;
    private boolean selectedUnit = false;
    private Position selectedPos;
    private ArrayList<String> availableUnits;
    private int i; // The index in the availableUnits list for the current Unit
    CompositeTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
        focusTool = new FocusTool(editor, game);
        endTurnTool = new EndTurnTool(editor, game);
        actionTool = new ActionTool(editor, game);
        availableUnits = ((GameImpl)game).getAvailableUnits();
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        Position currentPos = GfxConstants.getPositionFromXY(x, y);

        // Check if the player turn shield is clicked
        if (GfxConstants.TURN_SHIELD_X < x && x < GfxConstants.TURN_SHIELD_X + 27
                && GfxConstants.TURN_SHIELD_Y < y && y < GfxConstants.TURN_SHIELD_Y + 39) {
            endTurnTool.mouseDown(e, x, y);
            selectedPos = null;
            selectedUnit = false;
            return;
        }

        // If a city was clicked..
        if(selectedPos != null && game.getCityAt(selectedPos) != null) {
            // and the production icon is clicked next, change it
            if (GfxConstants.CITY_PRODUCTION_X < x && x < GfxConstants.CITY_PRODUCTION_X + 30
                    && GfxConstants.CITY_PRODUCTION_Y < y && y < GfxConstants.CITY_PRODUCTION_Y + 30) {
                productionIterator(selectedPos);
                game.setTileFocus(selectedPos);
            }

            // and the workforce focus icon is clicked, switch it to the other one
            if (GfxConstants.WORKFORCEFOCUS_X < x && x < GfxConstants.WORKFORCEFOCUS_X + 45
                    && GfxConstants.WORKFORCEFOCUS_Y < y && y < GfxConstants.WORKFORCEFOCUS_Y + 49) {
                switchWorkforce(selectedPos);
                game.setTileFocus(selectedPos);
            }
        }

        if(x < GfxConstants.MAP_OFFSET_X || GfxConstants.MAP_OFFSET_X + 16 * 30 < x
                || y < GfxConstants.MAP_OFFSET_Y || GfxConstants.MAP_OFFSET_Y + 16 * 30 < y)
            return;

        if (!selectedUnit) {
            focusTool.mouseDown(e, x, y);
            selectedUnit = game.getUnitAt(currentPos) != null;
            selectedPos = GfxConstants.getPositionFromXY(x, y);
            return;
        }

        // if you click the same unit twice, use its action
        if (selectedPos.equals(currentPos)) {
            actionTool.mouseDown(e, x, y);
            selectedPos = GfxConstants.getPositionFromXY(x, y);
            selectedUnit = false;
            return;
        }

        // if you have selected a unit and click anywhere in its movement range, move it
        if (Math.abs(selectedPos.getRow() - currentPos.getRow()) <= game.getUnitAt(selectedPos).getMoveCount()
                && Math.abs(selectedPos.getColumn() - currentPos.getColumn()) <= game.getUnitAt(selectedPos).getMoveCount()) {
            game.moveUnit(selectedPos, currentPos);
            game.setTileFocus(currentPos);
            selectedUnit = false; // deselect the unit
            selectedPos = GfxConstants.getPositionFromXY(x, y);
            return;
        }

        // else focus whatever you clicked on
        focusTool.mouseDown(e, x, y);
        selectedPos = GfxConstants.getPositionFromXY(x, y);
    }

    private void switchWorkforce(Position pos) {
        String focus = game.getCityAt(pos).getWorkforceFocus();
        if (focus.equals(GameConstants.foodFocus))
            game.changeWorkForceFocusInCityAt(pos, GameConstants.productionFocus);
        else
            game.changeWorkForceFocusInCityAt(pos, GameConstants.foodFocus);
    }

    private void productionIterator(Position pos) {
        i++;
        game.changeProductionInCityAt(pos, availableUnits.get(i % availableUnits.size()));
    }
}
