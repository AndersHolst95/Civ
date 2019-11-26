package hotciv.stub;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.ArrayList;

public class GameStub implements Game, frds.broker.Servant {
    private GameObserver observer = null;
    public boolean changeMe = false;
    public Position unitActionPerformedAt = null;
    private CityStub city = new CityStub(null, null);
    private UnitStub unit = new UnitStub(GameConstants.LEGION, null);

    private class UnitStub extends UnitImpl {
        private String objectId = "unit";

        public UnitStub(String type, Player owner) {
            super(type, owner);
        }

        public String getId() {
            return objectId;
        }
        public Player getOwner() {
            return Player.GREEN;
        }
        public int getDefensiveStrength() {
            return 64;
        }
        public int getAttackingStrength() {
            return 1337;
        }
        public int getMoveCount() {
            return 9;
        }
        public String getTypeString() {
            return "BigBoy";
        }
    }

    private class CityStub extends CityImpl {
        private String objectId = "city";

        public CityStub(Player owner, Position loc) {
            super(owner, loc);
        }

        public CityStub(int size, int treasury, Player owner, String production, String workforceFocus, Position loc) {
            super(size, treasury, owner, production, workforceFocus, loc);
        }

        public Player getOwner() {
            return Player.YELLOW;
        }
        public int getSize() {
            return 82;
        }
        public int getTreasury() {
            return 119;
        }
        public String getProduction() {
            return "dragon";
        }
        public String getWorkforceFocus() {
            return "vandland";
        }
        public String getId(){return objectId;}
    }

    public class TileStub extends TileImpl {
        private String objectId = "tile";
        private String type2;

        public TileStub(Position position, String type, CityImpl city, UnitImpl unit) {
            super(position, type, city, unit);
            this.type2 = type;
        }

        public String getTypeString() {
            return type2;
        }

        public String getId() {
            return objectId;
        }
    }

    TileStub tile = new TileStub(null, "oasis", null, null);
    public Tile getTileAt(Position pos) { return tile; }
    public Unit getUnitAt(Position pos) { return unit;}
    public City getCityAt(Position pos) { return city; }
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

    @Override
    public void requestUpdate() {

    }

    @Override
    public ArrayList<String> getAvailableUnits() {
        return null;
    }

    @Override
    public TileImpl[][] getTileMap() {
        return new TileImpl[0][];
    }
}
