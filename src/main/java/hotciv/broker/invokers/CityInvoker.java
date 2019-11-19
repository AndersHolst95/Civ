package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class CityInvoker implements frds.broker.Invoker {
    private Game servant;

    public CityInvoker(Game servant) {
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
                case OperationNames.getOwnerCity:
                    System.out.println("--> getOwnerCity called");
                    break;
                case OperationNames.getSize:
                    System.out.println("--> getSize called");
                    reply = new ReplyObject(0, gson.toJson(0));
                    break;
                case OperationNames.getTreasury:
                    System.out.println("--> getTreasury called");
                    reply = new ReplyObject(0, gson.toJson(0));
                    break;
                case OperationNames.getProduction:
                    System.out.println("--> getProduction called");
                    break;
                case OperationNames.getWorkforceFocus:
                    System.out.println("--> getWorkforceFocus called");
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }
}
