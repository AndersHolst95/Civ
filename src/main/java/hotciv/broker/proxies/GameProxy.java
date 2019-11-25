package hotciv.broker.proxies;

import frds.broker.Requestor;
import hotciv.broker.OperationNames;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

public class GameProxy implements Game {
    private Requestor requestor;
    private final String objectId = "lol";

    public GameProxy(Requestor requestor) {
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
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.endOfTurn, Void.class);
    }

    public void changeWorkForceFocusInCityAt(Position pos, String balance) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.changeWorkForceFocusInCityAt, Void.class, pos, balance);
    }

    public void changeProductionInCityAt(Position pos, String unitType) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.changeProductionInCityAt, Void.class, pos, unitType);
    }

    public void performUnitActionAt(Position pos) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.performUnitActionAt, Void.class, pos);
    }

    public void addObserver(GameObserver observer) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.addObserver, Void.class, observer);

    }

    public void setTileFocus(Position pos) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.setTileFocus, Void.class, pos);
    }

    @Override
    public void requestUpdate() {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.requestUpdate, Void.class);
    }
}
