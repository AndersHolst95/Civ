package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.framework.*;

public class UnitInvoker implements frds.broker.Invoker{
    private Game servant;

    public UnitInvoker(Game servant) {
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
                case OperationNames.getTypeStringUnit:
                    System.out.println("--> getTypeStringUnit called");
                    return new ReplyObject(0, "AndersAnd");
                case OperationNames.getOwnerUnit:
                    System.out.println("--> getOwnerUnit called");
                    return new ReplyObject(0, gson.toJson(Player.GREEN));
                case OperationNames.getMoveCount:
                    System.out.println("--> getMoveCount called");
                    reply = new ReplyObject(0, gson.toJson(100));
                case OperationNames.getDefensiveStrength:
                    System.out.println("--> getDefensiveStrength called");
                    reply = new ReplyObject(0, gson.toJson(100));
                case OperationNames.getAttackingStrength:
                    System.out.println("--> getAttackingStrength called");
                    reply = new ReplyObject(0, gson.toJson(100));

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }
}
