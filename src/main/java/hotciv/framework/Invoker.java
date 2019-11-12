package hotciv.framework;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.standard.TileImpl;

public class Invoker {
    Game servant;

    public Invoker(Game servant) {
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
                case OperationNames.addObserver:
                case OperationNames.changeProductionInCityAt:
                case OperationNames.changeWorkForceFocusInCityAt:
                case OperationNames.createUnit:
                case OperationNames.endOfTurn:
                case OperationNames.getAge:
                    reply = new ReplyObject(0, gson.toJson(servant.getAge()));
                    break;
                case OperationNames.getCityAt:
                case OperationNames.getPlayerInTurn:
                case OperationNames.getTileAt:
                    pos = gson.fromJson(array.get(0), Position.class);
                    reply = new ReplyObject(0, gson.toJson(servant.getTileAt(pos)));
                    break;
                case OperationNames.getUnitAt:
                case OperationNames.getWinner:
                case OperationNames.moveUnit:
                    Position from = gson.fromJson(array.get(0), Position.class);
                    Position to = gson.fromJson(array.get(1), Position.class);
                    reply = new ReplyObject(0, gson.toJson(servant.moveUnit(from, to)));
                    break;
                case OperationNames.performUnitActionAt:
                case OperationNames.setCityAt:
                case OperationNames.setProduction:
                case OperationNames.setTileFocus:
                    pos = gson.fromJson(array.get(0), Position.class);
                    servant.setTileFocus(pos);
                    reply = new ReplyObject(0, "");
                    break;
                case OperationNames.setTypeAt:
                case OperationNames.setUnitAt:
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }
}
