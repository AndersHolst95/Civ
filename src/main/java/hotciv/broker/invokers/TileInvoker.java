package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.TileImpl;

import java.util.HashMap;

public class TileInvoker implements frds.broker.Invoker {
    private Game servant;

    public TileInvoker(Game servant) {
        this.servant = servant;
    }

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        Tile tile =  Invoker.getTile(objectId);
        Gson gson = new Gson();

        try {
            switch (operationName) {
                case OperationNames.getTypeStringTile:
                    return new ReplyObject(0, gson.toJson(tile.getTypeString()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ReplyObject(0, "");
    }
}
