package hotciv.broker;

import hotciv.framework.*;

public class Servant implements Role {
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
        return 0;
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

    public void addObserver(GameObserver observer) {

    }

    public void setTileFocus(Position pos) {

    }
}
