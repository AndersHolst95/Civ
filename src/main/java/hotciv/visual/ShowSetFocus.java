package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

public class ShowSetFocus {
    public static void main(String[] args) {
        Game game = new StubGame2();

        DrawingEditor editor = new MiniDrawApplication("Click any tile to set focus", new HotCivFactory4(game));
        editor.open();
        editor.showStatus("Click a tile to see Game's setFocus method being called.");

        // TODO: Replace the setting of the tool with your SetFocusTool implementation. - DONE
        editor.setTool(new FocusTool(editor, game));
    }
}

class FocusTool extends NullTool {
    private Game game;
    private DrawingEditor editor;

    public FocusTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        game.setTileFocus(GfxConstants.getPositionFromXY(x, y));
        editor.showStatus("Inspecting selected tile!");
    }
}
