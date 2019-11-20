package hotciv.stub;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

public class GameStub implements Game, frds.broker.Servant {
    private GameObserver observer = null;
    public boolean changeMe = false;
    public Position unitActionPerformedAt = null;
    private CityStub city = new CityStub();

    private class CityStub implements City {

        private String id = "1";

        public Player getOwner() {
            return null;
        }

        public int getSize() {
            return 0;
        }

        public int getTreasury() {
            return 0;
        }

        public String getProduction() {
            return null;
        }

        public String getWorkforceFocus() {
            return null;
        }
        public String getId(){return id;}
    }

    public class TileStub implements Tile{
        private String id = "tile";
        public String getTypeString() {
            return "oasis";
        }

        public String getId() {
            return id;
        }
    }

    TileStub tile = new TileStub();
    public Tile getTileAt(Position pos) { return tile; }
    public Unit getUnitAt(Position pos) { return new UnitImpl(GameConstants.ARCHER, Player.YELLOW);}
    public City getCityAt(Position pos) { return new CityImpl(Player.YELLOW, null); }
    public Player getPlayerInTurn() { return Player.YELLOW; }
    public Player getWinner() { return Player.YELLOW; }
    public int getAge() { return 16; }
    public boolean moveUnit(Position from, Position to) { return true;}
    public void endOfTurn() {changeMe = true;}
    public void changeWorkForceFocusInCityAt(Position pos, String balance) {changeMe = true;}
    public void changeProductionInCityAt(Position pos, String unitType) { changeMe = true;}
    public void performUnitActionAt(Position pos) { unitActionPerformedAt = pos;}
    public void addObserver(GameObserver observer) { changeMe = true;}
    public void setTileFocus(Position pos) {changeMe = true;}
}
