package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class TileInvoker implements frds.broker.Invoker {
    private Game servant;

    public TileInvoker(Game servant) {
        this.servant = servant;
    }

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        ReplyObject reply = new ReplyObject(0, "");
        Gson gson = new Gson();

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();
        Position pos;
        try {
            switch (operationName) {
                case OperationNames.getTypeStringTile:
                    System.out.println("--> getTypeStringTile called");
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }
}
