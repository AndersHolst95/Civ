package hotciv.broker.invokers;

import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.broker.Servant;
import hotciv.framework.Game;
import hotciv.framework.Tile;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class Invoker implements frds.broker.Invoker {
    Servant servant;
    GameInvoker gameInvoker;
    UnitInvoker unitInvoker;
    CityInvoker cityInvoker;
    TileInvoker tileInvoker;
    private static HashMap<String, TileImpl> tileMap = new HashMap<>();
    private static HashMap<String, UnitImpl> unitMap = new HashMap<>();
    private static HashMap<String, CityImpl> cityMap = new HashMap<>();

    public static TileImpl getTile(String id) {
        return tileMap.get(id);
    }

    public static void addTile(TileImpl tile){
        tileMap.put(tile.getId(), tile);
    }

    public static UnitImpl getUnit(String id) {
        return unitMap.get(id);
    }

    public static void addUnit(UnitImpl unit){
        unitMap.put(unit.getId(), unit);
    }

    public static CityImpl getCity(String id) {
        return cityMap.get(id);
    }
    public static void addCity(CityImpl city){
        cityMap.put(city.getId(), city);
    }

    public Invoker(Game game) {
        this.servant = new Servant(game);
        gameInvoker = new GameInvoker(servant);
        unitInvoker = new UnitInvoker();
        cityInvoker = new CityInvoker();
        tileInvoker = new TileInvoker();
    }


    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        String fromClass = operationName.substring(0, operationName.indexOf('_')+1);
        switch(fromClass) {
            case OperationNames.game:
                return gameInvoker.handleRequest(objectId, operationName, payload);
            case OperationNames.city:
                return cityInvoker.handleRequest(objectId, operationName, payload);
            case OperationNames.tile:
                return tileInvoker.handleRequest(objectId, operationName, payload);
            case OperationNames.unit:
                return unitInvoker.handleRequest(objectId, operationName, payload);
            default:
                return null;
        }
    }
}
