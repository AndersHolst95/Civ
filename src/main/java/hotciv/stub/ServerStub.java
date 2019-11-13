package hotciv.stub;

import hotciv.framework.*;
import hotciv.standard.UnitImpl;

public class ServerStub implements Game {
    Position archer = new Position(1, 1);
    public Tile getTileAt(Position p) {
        return null;
    }

    public Unit getUnitAt(Position p) {
        System.out.println("called getUnitAt");
        if (p.equals(archer))
            return new UnitImpl(GameConstants.ARCHER, Player.RED);
        return null;
    }

    public City getCityAt(Position p) {
        return null;
    }

    public Player getPlayerInTurn() {
        System.out.println("I have now called the getPLayerInTurn!");
        return Player.GREEN;
    }

    public Player getWinner() {
        return null;
    }

    public int getAge() {
        System.out.println("Called get age!");
        return 19;
    }

    public boolean moveUnit(Position from, Position to) {
        System.out.println("called moveUnit");
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
}
