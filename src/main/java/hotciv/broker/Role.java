package hotciv.broker;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

public interface Role {
    Tile getTileAt(Position pos);
    Unit getUnitAt(Position pos);
    City getCityAt(Position pos);
    Player getPlayerInTurn();
    Player getWinner();
    int getAge();
    boolean moveUnit(Position from, Position to);
    void endOfTurn();
    void changeWorkForceFocusInCityAt(Position pos, String balance);
    void changeProductionInCityAt(Position pos, String unitType);
    void performUnitActionAt(Position pos);
    void addObserver(GameObserver observer);
    void setTileFocus(Position pos);
}
