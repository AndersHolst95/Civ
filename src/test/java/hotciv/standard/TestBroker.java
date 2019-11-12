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
        boolean changeMe = false;

        TileImpl tile = new TileImpl(new Position(7, 7), "oasis", null, null);
        public Tile getTileAt(Position pos) { return tile; }
        public Unit getUnitAt(Position pos) { return new UnitImpl(GameConstants.ARCHER, Player.YELLOW);}
        public City getCityAt(Position pos) { return new CityImpl(Player.YELLOW, null); }
        public Player getPlayerInTurn() { return Player.YELLOW; }
        public Player getWinner() { return Player.YELLOW; }
        public int getAge() { return 16; }
        public boolean moveUnit(Position from, Position to) { return true; }
        public void endOfTurn() { }
        public void changeWorkForceFocusInCityAt(Position pos, String balance) { }
        public void changeProductionInCityAt(Position pos, String unitType) { }
        public void performUnitActionAt(Position pos) { }
        public void addObserver(GameObserver observer) { }
        public void setTileFocus(Position pos) {changeMe = true;}
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
    public void getUnitAtCall(){
        assertThat(client.getUnitAt(null).getOwner(), is(Player.YELLOW));
    }

    @Test
    public void getCityAtCall(){
        assertThat(client.getCityAt(null).getOwner(), is(Player.YELLOW));
    }

    @Test
    public void getPlayerInTurnCall(){
        assertThat(client.getPlayerInTurn(), is(Player.YELLOW));
    }

    @Test
    public void getWinnerCall(){
        assertThat(client.getWinner(), is(Player.YELLOW));
    }


    @Test
    public void moveUnitCall() {
        assertTrue(client.moveUnit(null, null));
    }

    @Test
    public void setTileFocus() {
        client.setTileFocus(null);
        assertTrue(servant.changeMe);
    }


}

