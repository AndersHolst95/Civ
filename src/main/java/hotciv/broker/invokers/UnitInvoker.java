package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import hotciv.stub.GameStub;

public class UnitInvoker implements frds.broker.Invoker{
    private Game servant;

    public UnitInvoker(Game servant) {
        this.servant = servant;
    }

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        ReplyObject reply = new ReplyObject(0, "");
        Gson gson = new Gson();
        UnitImpl unit = Invoker.getUnit(objectId);

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();
        Position pos;
        try {
            switch (operationName) {
                case OperationNames.getTypeStringUnit:
                    return new ReplyObject(0, gson.toJson(unit.getTypeString()));
                case OperationNames.getOwnerUnit:
                    return new ReplyObject(0, gson.toJson(unit.getOwner()));
                case OperationNames.getMoveCount:
                    reply = new ReplyObject(0, gson.toJson(unit.getMoveCount()));
                case OperationNames.getDefensiveStrength:
                    reply = new ReplyObject(0, gson.toJson(unit.getDefensiveStrength()));
                case OperationNames.getAttackingStrength:
                    reply = new ReplyObject(0, gson.toJson(unit.getAttackingStrength()));

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }
}
