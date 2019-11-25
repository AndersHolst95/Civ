package hotciv.broker.invokers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.broker.Servant;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

public class GameInvoker implements frds.broker.Invoker{
    private Game game;

    public GameInvoker(Servant servant) {
        game = servant.getGame();
    }

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        Gson gson = new Gson();

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(payload).getAsJsonArray();
        Position pos;
        try {
            switch (operationName) {
                case OperationNames.getAge:
                    return new ReplyObject(0, gson.toJson(game.getAge()));
                case OperationNames.getCityAt:
                    pos = gson.fromJson(array.get(0), Position.class);
                    City city = game.getCityAt(pos);
                    if (city != null)
                        Invoker.addCity(((CityImpl) city));
                    return new ReplyObject(0, gson.toJson(city));
                case OperationNames.getPlayerInTurn:
                    return new ReplyObject(0, gson.toJson(game.getPlayerInTurn()));
                case OperationNames.getTileAt:
                    pos = gson.fromJson(array.get(0), Position.class);
                    Tile tile = game.getTileAt(pos);
                    if (tile != null)
                        Invoker.addTile(((TileImpl) tile));
                    return new ReplyObject(0, gson.toJson(tile));
                case OperationNames.getUnitAt:
                    pos = gson.fromJson(array.get(0), Position.class);
                    Unit unit = game.getUnitAt(pos);
                    if (unit != null)
                        Invoker.addUnit(((UnitImpl) unit));
                    return new ReplyObject(0, gson.toJson(unit));
                case OperationNames.getWinner:
                    return new ReplyObject(0, gson.toJson(game.getWinner()));
                case OperationNames.getAvailableUnits:
                    return new ReplyObject(0, gson.toJson(game.getAvailableUnits()));
                case OperationNames.moveUnit:
                    Position from = gson.fromJson(array.get(0), Position.class);
                    Position to = gson.fromJson(array.get(1), Position.class);
                    return new ReplyObject(0, gson.toJson(game.moveUnit(from, to)));
                case OperationNames.performUnitActionAt:
                    pos = gson.fromJson(array.get(0), Position.class);
                    game.performUnitActionAt(pos);
                    break;
                case OperationNames.setTileFocus:
                    pos = gson.fromJson(array.get(0), Position.class);
                    game.setTileFocus(pos);
                    break;
                case OperationNames.changeProductionInCityAt:
                    pos = gson.fromJson(array.get(0), Position.class);
                    String unitType = gson.fromJson(array.get(1), String.class);
                    game.changeProductionInCityAt(pos, unitType);
                    break;
                case OperationNames.changeWorkForceFocusInCityAt:
                    pos = gson.fromJson(array.get(0), Position.class);
                    String balance = gson.fromJson(array.get(1), String.class);
                    game.changeWorkForceFocusInCityAt(pos, balance);
                    break;
                case OperationNames.endOfTurn:
                    game.endOfTurn();
                    break;
                case OperationNames.requestUpdate:
                    game.requestUpdate();
                    break;

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ReplyObject(0, "");
    }
}
