package hotciv.standard;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestBroker {
    ClientProxy client;
    GameStub servant = new GameStub();

    @Before
    public void setup() {
        GameObserver nullObserver = new NullObserver();
        servant = new GameStub();
        servant.addObserver(nullObserver);
        Invoker invoker = new Invoker(servant);
        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        client = new ClientProxy(requestor);
    }

    private static class GameStub implements Game, frds.broker.Servant {
        boolean changeme = false;
        public boolean isChanged() {
            return changeme;
        }

        TileImpl tile = new TileImpl(new Position(7, 7), "oasis", null, null);
        public Tile getTileAt(Position pos) { return tile; }
        public Unit getUnitAt(Position pos) { return null; }
        public City getCityAt(Position pos) { return null; }
        public Player getPlayerInTurn() { return null; }
        public Player getWinner() { return null; }
        public int getAge() { return 16; }
        public UnitImpl createUnit(String type, Player owner) { return null; }
        public boolean setProduction(String production, CityImpl city) { return false; }
        public boolean moveUnit(Position from, Position to) { return true; }
        public void endOfTurn() { }
        public void changeWorkForceFocusInCityAt(Position pos, String balance) { }
        public void changeProductionInCityAt(Position pos, String unitType) { }
        public void performUnitActionAt(Position pos) { }
        public boolean setUnitAt(Position pos, UnitImpl unit) { return false; }
        public void setTypeAt(Position pos, String type) { }
        public void setCityAt(CityImpl city) { }
        public void addObserver(GameObserver observer) { }
        public void setTileFocus(Position pos) {
            changeme = true;
        }
    }

    @Test
    public void getAgeCall() {
        assertEquals(16, client.getAge());
    }

    @Test
    public void getTileAtCall() {
        assertEquals("oasis", client.getTileAt(null).getTypeString());
    }

    @Test
    public void moveUnitCall() {
        assertTrue(client.moveUnit(null, null));
    }

    @Test
    public void setTileFocus() {
        client.setTileFocus(null);
        assertTrue(servant.isChanged());
    }
}

