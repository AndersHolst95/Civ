package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class TileInvoker implements frds.broker.Invoker {

    private Invoker invoker;
    private Game servant;

    public TileInvoker(hotciv.broker.invokers.Invoker invoker) {
        this.invoker = invoker;
        servant = invoker.getServant();
    }

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        ReplyObject reply = null;
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
