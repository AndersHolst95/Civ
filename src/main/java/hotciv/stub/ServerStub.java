package hotciv.stub;

import hotciv.framework.*;
import hotciv.standard.UnitImpl;

import java.util.ArrayList;

public class ServerStub implements Game {
    Position archer = new Position(1, 1);
    public Tile getTileAt(Position p) {
        return null;
    }

    public Unit getUnitAt(Position p) {
        System.out.println("called getUnitAt: RED ARCHER");
        if (p.equals(archer))
            return new UnitImpl(GameConstants.ARCHER, Player.RED);
        return null;
    }

    public City getCityAt(Position p) {
        return null;
    }

    public Player getPlayerInTurn() {
        System.out.println("Called getPLayerInTurn: PLAYER GREEN");
        return Player.GREEN;
    }

    public Player getWinner() {
        return null;
    }

    public int getAge() {
        System.out.println("Called getAge: AGE 19");
        return 19;
    }

    public boolean moveUnit(Position from, Position to) {
        System.out.println("Called moveUnit: UNIT HAS BEEN MOOVED");
        archer = to;
        return false;
    }

    public void endOfTurn() {

    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {

    }

    public void changeProductionInCityAt(Position p, String unitType) {

    }

    public void performUnitActionAt(Position p) {

    }

    public void addObserver(GameObserver observer) {

    }

    public void setTileFocus(Position position) {

    }

    @Override
    public void requestUpdate() {

    }

    @Override
    public ArrayList<String> getAvailableUnits() {
        return null;
    }
}
