package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

public class ShowMove {
    public static void main(String[] args) {
        Game game = new StubGame2();

        DrawingEditor editor = new MiniDrawApplication( "Move any unit using the mouse", new HotCivFactory4(game));
        editor.open();
        editor.showStatus("Move units to see Game's moveUnit method being called.");

        // TODO: Replace the setting of the tool with your UnitMoveTool implementation. - DONE
        editor.setTool( new MoveTool(editor, game) );
    }
}

class MoveTool extends NullTool {
    private Game game;
    private DrawingEditor editor;

    private boolean selectedUnit = false;
    private Position fromPos;

    public MoveTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        if (!selectedUnit) {
            if (game.getUnitAt(GfxConstants.getPositionFromXY(x, y)) != null) {
                editor.showStatus( "Unit selected! Click again to move.." );
                fromPos = GfxConstants.getPositionFromXY(x, y);
                selectedUnit = true;
            }
        }
        else {
            editor.showStatus( "Unit moved!" );
            game.moveUnit(fromPos, GfxConstants.getPositionFromXY(x, y));
            selectedUnit = false;
        }
    }
}
