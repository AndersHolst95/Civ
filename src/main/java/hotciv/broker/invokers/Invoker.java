package hotciv.broker.invokers;

import frds.broker.ReplyObject;
import hotciv.broker.OperationNames;
import hotciv.framework.Game;

public class Invoker implements frds.broker.Invoker {
    Game servant;
    GameInvoker gameInvoker;
    UnitInvoker unitInvoker;
    CityInvoker cityInvoker;
    TileInvoker tileInvoker;

    public Invoker(Game servant) {
        this.servant = servant;
        gameInvoker = new GameInvoker(servant);
        unitInvoker = new UnitInvoker(servant);
        cityInvoker = new CityInvoker(servant);
        tileInvoker = new TileInvoker(servant);
    }

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        String fromClass = operationName.substring(0, operationName.indexOf('_'));
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
