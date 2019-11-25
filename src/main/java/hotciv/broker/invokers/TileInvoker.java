package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

public class TileInvoker implements frds.broker.Invoker {
    public TileInvoker() {}

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        TileImpl tile =  Invoker.getTile(objectId);
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();

        try {
            switch (operationName) {
                case OperationNames.getTypeStringTile:
                    return new ReplyObject(0, gson.toJson(tile.getTypeString()));
                case OperationNames.setType:
                    String type = gson.fromJson(array.get(0), String.class);
                    tile.setType(type);
                    break;
                case OperationNames.setCity:
                    CityImpl city = gson.fromJson(array.get(0), CityImpl.class);
                    tile.setCity(city);
                    break;
                case OperationNames.setUnit:
                    UnitImpl unit = gson.fromJson(array.get(0), UnitImpl.class);
                    tile.setUnit(unit);
                    break;
                case OperationNames.getUnit:
                    return new ReplyObject(0, gson.toJson(tile.getUnit()));
                case OperationNames.getCity:
                    return new ReplyObject(0, gson.toJson(tile.getCity()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ReplyObject(0, "");
    }
}