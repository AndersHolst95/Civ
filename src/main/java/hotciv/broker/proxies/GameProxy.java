package hotciv.broker.proxies;

import frds.broker.Requestor;
import hotciv.broker.OperationNames;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameProxy implements Game {
    private Requestor requestor;
    private final String objectId = "lol";
    private ArrayList<GameObserver> observers = new ArrayList<>();

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
        boolean hasMoved = requestor.sendRequestAndAwaitReply(objectId, OperationNames.moveUnit, Boolean.class, from, to);
        Utility.notifyWorldChange(from, observers);
        Utility.notifyWorldChange(to, observers);
        return hasMoved;
    }

    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.endOfTurn, Void.class);
        Utility.notifyTurnChange(getPlayerInTurn(), observers);
    }

    public void changeWorkForceFocusInCityAt(Position pos, String balance) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.changeWorkForceFocusInCityAt, Void.class, pos, balance);
        Utility.notifyWorldChange(pos, observers);
    }

    public void changeProductionInCityAt(Position pos, String unitType) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.changeProductionInCityAt, Void.class, pos, unitType);
        Utility.notifyWorldChange(pos, observers);
    }

    public void performUnitActionAt(Position pos) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.performUnitActionAt, Void.class, pos);
        Utility.notifyWorldChange(pos, observers);
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void setTileFocus(Position pos) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.setTileFocus, Void.class, pos);
        Utility.notifyTileFocusChange(pos, observers);
    }

    public void requestUpdate() {
        for(GameObserver observer : observers)
            observer.requestUpdate();
    }

    @Override
    public ArrayList<String> getAvailableUnits() {
        String[] asArray = requestor.sendRequestAndAwaitReply(objectId, OperationNames.getAvailableUnits, String[].class);
        List<String> arraylist = Arrays.asList(asArray);
        return new ArrayList<String>(arraylist);
    }

    @Override
    public TileImpl[][] getTileMap() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getTileMap, TileImpl[][].class);
    }
}
