package hotciv.broker;

import hotciv.broker.proxies.GameProxy;
import hotciv.framework.Game;
import hotciv.visual.CompositeTool;
import hotciv.visual.HotCivFactory4;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

public class JoinServer {
    private static String ip = "10.192.138.211";
    private static Client client;

    public static void main(String[] args) {
        client = new Client(ip, 2800);
        DrawingEditor editor = new MiniDrawApplication("HotCiv", new HotCivFactory4(client.getGameProxy()));
        editor.open();
        editor.setTool(new CompositeTool(editor, client.getGameProxy()));
    }
}
