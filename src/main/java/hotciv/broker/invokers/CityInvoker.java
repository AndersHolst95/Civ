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
    public CityInvoker(){}

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        Gson gson = new Gson();
        CityImpl city = Invoker.getCity(objectId);

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();
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
                case OperationNames.getFood:
                    return new ReplyObject(0, gson.toJson(city.getFood()));
                case OperationNames.getLocation:
                    return new ReplyObject(0, gson.toJson(city.getLocation()));
                case OperationNames.getProductionCost:
                    return new ReplyObject(0, gson.toJson(city.getProductionCost()));
                case OperationNames.decrementSize:
                    return new ReplyObject(0, gson.toJson(city.decrementSize()));
                case OperationNames.addProductionValue:
                    int prodVal = gson.fromJson(array.get(0), Integer.class);
                    city.addProductionValue(prodVal);
                    break;
                case OperationNames.setProduction:
                    String prodString = gson.fromJson(array.get(0), String.class);
                    city.setProduction(prodString);
                    break;
                case OperationNames.setOwner:
                    Player owner = gson.fromJson(array.get(0), Player.class);
                    city.setOwner(owner);
                    break;
                case OperationNames.resetFood:
                    city.resetFood();
                    break;
                case OperationNames.increaseSize:
                    city.increaseSize();
                    break;
                case OperationNames.addFood:
                    int food = gson.fromJson(array.get(0), Integer.class);
                    city.addFood(food);
                    break;
                case OperationNames.setWorkforceFocus:
                    String workforceFocus = gson.fromJson(array.get(0), String.class);
                    city.setWorkforceFocus(workforceFocus);
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ReplyObject(0, "");
    }
}
