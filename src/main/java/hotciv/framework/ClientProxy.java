package hotciv.framework;

import frds.broker.Requestor;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.OperationNames;

public class ClientProxy implements Role {
    private Requestor requestor;
    private final String objectId = "lol";

    public ClientProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    public Tile getTileAt(Position pos) {
        return null;
    }

    public Unit getUnitAt(Position pos) {
        return null;
    }

    public City getCityAt(Position pos) {
        return null;
    }

    public Player getPlayerInTurn() {
        return null;
    }

    public Player getWinner() {
        return null;
    }

    public int getAge() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getAge, "int");
    }

    public UnitImpl createUnit(String type, Player owner) {
        return null;
    }

    public boolean setProduction(String production, CityImpl city) {
        return false;
    }

    public boolean moveUnit(Position from, Position to) {
        return false;
    }

    public void endOfTurn() {

    }

    public void changeWorkForceFocusInCityAt(Position pos, String balance) {

    }

    public void changeProductionInCityAt(Position pos, String unitType) {

    }

    public void performUnitActionAt(Position pos) {

    }

    public boolean setUnitAt(Position pos, UnitImpl unit) {
        return false;
    }

    public void setTypeAt(Position pos, String type) {

    }

    public void setCityAt(CityImpl city) {

    }

    public void addObserver(GameObserver observer) {

    }

    public void setTileFocus(Position pos) {

    }
}
