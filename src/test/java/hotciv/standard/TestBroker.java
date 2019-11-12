package hotciv.standard;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.*;
import hotciv.standard.factory.DeltaFactory;
import hotciv.standard.factory.SemiFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestBroker {
    private ClientProxy client;
    ClientProxy game;
    Game servant = new GameStub();

    @Before
    public void setup() {
        GameObserver nullObserver = new NullObserver();
        servant = new GameStub();
        servant.addObserver(nullObserver);
        Invoker invoker = new GameInvoker();
        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        game = new ClientProxy(requestor);
    }

    private static class GameStub implements Game, frds.broker.Servant {
        public Tile getTileAt(Position pos) { return null; }
        public Unit getUnitAt(Position pos) { return null; }
        public City getCityAt(Position pos) { return null; }
        public Player getPlayerInTurn() { return null; }
        public Player getWinner() { return null; }
        public int getAge() { return 16; }
        public UnitImpl createUnit(String type, Player owner) { return null; }
        public boolean setProduction(String production, CityImpl city) { return false; }
        public boolean moveUnit(Position from, Position to) { return false; }
        public void endOfTurn() { }
        public void changeWorkForceFocusInCityAt(Position pos, String balance) { }
        public void changeProductionInCityAt(Position pos, String unitType) { }
        public void performUnitActionAt(Position pos) { }
        public boolean setUnitAt(Position pos, UnitImpl unit) { return false; }
        public void setTypeAt(Position pos, String type) { }
        public void setCityAt(CityImpl city) { }
        public void addObserver(GameObserver observer) { }
        public void setTileFocus(Position pos) { }
    }

    @Test
    public void getAgeCall() {
        assertEquals(16, client.getAge());
    }
}

