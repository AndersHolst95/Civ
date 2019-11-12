package hotciv.framework;

import frds.broker.Requestor;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

public class ClientProxy implements Role {
    private Requestor requestor;
    private final String objectId = "lol";

    public ClientProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    public Tile getTileAt(Position pos) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getTileAt, TileImpl.class, pos);
    }

    public Unit getUnitAt(Position pos) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getUnitAt, UnitImpl.class, pos);
    }

    public City getCityAt(Position pos) {
        return requestor.sendRequestAndAwaitReply(objectId,OperationNames.getCityAt, CityImpl.class, pos);
    }

    public Player getPlayerInTurn() {

        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getPlayerInTurn, Player.class);
    }

    public Player getWinner() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getWinner, Player.class);
    }

    public int getAge() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getAge, Integer.class);
    }


    public boolean moveUnit(Position from, Position to) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.moveUnit, Boolean.class, from, to);
    }

    public void endOfTurn() {

    }

    public void changeWorkForceFocusInCityAt(Position pos, String balance) {

    }

    public void changeProductionInCityAt(Position pos, String unitType) {

    }

    public void performUnitActionAt(Position pos) {

    }

    public void addObserver(GameObserver observer) {

    }

    public void setTileFocus(Position pos) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.setTileFocus, Void.class, pos);
    }
}
