package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.NullObserver;
import hotciv.framework.Position;

public class UnitInvoker implements frds.broker.Invoker{
    private Game servant;

    public UnitInvoker(Game servant) {
        this.servant = servant;
    }

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        ReplyObject reply = null;
        Gson gson = new Gson();

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();
        Position pos;
        try {
            switch (operationName) {
                case OperationNames.getTypeStringUnit:
                    System.out.println("--> getTypeStringUnit called");
                    break;
                case OperationNames.getOwnerUnit:
                    System.out.println("--> getOwnerUnit called");
                    break;
                case OperationNames.getMoveCount:
                    System.out.println("--> getMoveCount called");
                    break;
                case OperationNames.getDefensiveStrength:
                    System.out.println("--> getDefensiveStrength called");
                    break;
                case OperationNames.getAttackingStrength:
                    System.out.println("--> getAttackingStrength called");
                    break;

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }
}
