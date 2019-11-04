package hotciv.visual;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.standard.factory.SemiFactory;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class SemiGUI {
    public static void main(String[] args) {
        GameImpl game = new GameImpl(new SemiFactory());
        DrawingEditor editor = new MiniDrawApplication("SemiCiv", new HotCivFactory4(game)); //Might work?
        editor.open();
        editor.setTool(new CompositeTool(editor, game));
    }
}

