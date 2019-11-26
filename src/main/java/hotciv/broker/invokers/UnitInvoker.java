package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.standard.UnitImpl;

public class UnitInvoker implements frds.broker.Invoker{
    public UnitInvoker() {}

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        Gson gson = new Gson();
        UnitImpl unit = Invoker.getUnit(objectId);

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();
        try {
            switch (operationName) {
                case OperationNames.getTypeStringUnit:
                    return new ReplyObject(0, gson.toJson(unit.getTypeString()));
                case OperationNames.getOwnerUnit:
                    return new ReplyObject(0, gson.toJson(unit.getOwner()));
                case OperationNames.getMoveCount:
                    return new ReplyObject(0, gson.toJson(unit.getMoveCount()));
                case OperationNames.getDefensiveStrength:
                    return new ReplyObject(0, gson.toJson(unit.getDefensiveStrength()));
                case OperationNames.getAttackingStrength:
                    return new ReplyObject(0, gson.toJson(unit.getAttackingStrength()));
                case OperationNames.getUsedAction:
                    return new ReplyObject(0, gson.toJson(unit.getUsedAction()));
                case OperationNames.setMoveCount:
                    int i = gson.fromJson(array.get(0), Integer.class);
                    unit.setMoveCount(i);
                    break;
                case OperationNames.toggleFortify:
                    unit.toggleFortify();
                    break;
                case OperationNames.setUsedAction:
                    boolean usedAction = gson.fromJson(array.get(0), boolean.class);
                    unit.setUsedAction(usedAction);
                    break;
                case OperationNames.refreshMoveCount:
                    unit.refreshMoveCount();
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ReplyObject(0, "");
    }
}