package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

public class ShowEndOfTurn {
    public static void main(String[] args) {
        Game game = new StubGame2();

        DrawingEditor editor = new MiniDrawApplication( "Click top shield to end the turn", new HotCivFactory4(game));
        editor.open();
        editor.showStatus("Click to shield to see Game's endOfTurn method being called.");

        // TODO: Replace the setting of the tool with your EndOfTurnTool implementation. - DONE
        editor.setTool(new EndTurnTool(editor, game));
    }
}

class EndTurnTool extends NullTool {
    private Game game;
    private DrawingEditor editor;

    public EndTurnTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        game.endOfTurn();
        editor.showStatus("Turn ended!");
    }
}

