package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;

public class CityInvoker implements frds.broker.Invoker {
    private Game servant;

    public CityInvoker(Game servant) {
        this.servant = servant;
    }

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        ReplyObject reply = new ReplyObject(0, "");
        Gson gson = new Gson();
        CityImpl city = Invoker.getCity(objectId);

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();
        Position pos;
        try {
            switch (operationName) {
                case OperationNames.getOwnerCity:
                    return new ReplyObject(0, gson.toJson(city.getOwner()));
                case OperationNames.getSize:
                    return new ReplyObject(0,gson.toJson(city.getSize()));
                case OperationNames.getTreasury:
                    return new ReplyObject(0, gson.toJson(city.getTreasury()));
                case OperationNames.getProduction:
                    return new ReplyObject(0, gson.toJson(city.getProduction()));
                case OperationNames.getWorkforceFocus:
                    return new ReplyObject(0, gson.toJson(city.getWorkforceFocus()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }
}
