package hotciv.broker.proxies;

import frds.broker.Requestor;
import hotciv.broker.OperationNames;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.ArrayList;

public class GameProxy implements Game {
    private Requestor requestor;
    private final String objectId = "lol";
    private ArrayList<GameObserver> observers;

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
        boolean hasMooved = requestor.sendRequestAndAwaitReply(objectId, OperationNames.moveUnit, Boolean.class, from, to);
        Utility.notifyWorldChange(from, observers);
        Utility.notifyWorldChange(to, observers);
        return hasMooved;
    }

    public void endOfTurn() {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.endOfTurn, Void.class);
        Player currentPlayer = getPlayerInTurn();
        if(currentPlayer.equals(Player.RED))
            Utility.notifyTurnChange(Player.BLUE, observers);
        else Utility.notifyTurnChange(Player.RED, observers);
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
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getAvailableUnits, ArrayList.class);
    }
}
