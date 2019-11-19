package hotciv.standard;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.broker.invokers.Invoker;
import hotciv.broker.LocalMethodClientRequestHandler;
import hotciv.broker.proxies.CityProxy;
import hotciv.broker.proxies.GameProxy;
import hotciv.broker.proxies.TileProxy;
import hotciv.broker.proxies.UnitProxy;
import hotciv.framework.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestBroker {
    GameProxy gameProxy;
    CityProxy cityProxy;
    UnitProxy unitProxy;
    TileProxy tileProxy;


    GameStub servant = new GameStub();

    @Before
    public void setup() {
        GameObserver nullObserver = new NullObserver();
        servant = new GameStub();
        servant.addObserver(nullObserver);
        Invoker invoker = new Invoker(servant);
        ClientRequestHandler crh = new LocalMethodClientRequestHandler(invoker);
        Requestor requestor = new StandardJSONRequestor(crh);
        gameProxy = new GameProxy(requestor);
        cityProxy = new CityProxy(requestor);
        unitProxy = new UnitProxy(requestor);
        tileProxy = new TileProxy(requestor);
    }

    private static class GameStub implements Game, frds.broker.Servant {
        GameObserver observer = null;
        boolean changeMe = false;
        Position unitActionPerformedAt = null;


        TileImpl tile = new TileImpl(new Position(7, 7), "oasis", null, null);
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

    @Test
    public void getAgeCall() {
        assertEquals(16, gameProxy.getAge());
    }

    @Test
    public void getTileAtCall() {
        assertEquals("oasis", gameProxy.getTileAt(null).getTypeString());
    }


    @Test
    public void getUnitAtCall(){
        assertThat(gameProxy.getUnitAt(null).getOwner(), is(Player.YELLOW));
    }

    @Test
    public void getCityAtCall(){
        assertThat(gameProxy.getCityAt(null).getOwner(), is(Player.YELLOW));
    }

    @Test
    public void getPlayerInTurnCall(){
        assertThat(gameProxy.getPlayerInTurn(), is(Player.YELLOW));
    }

    @Test
    public void getWinnerCall(){
        assertThat(gameProxy.getWinner(), is(Player.YELLOW));
    }

    @Test
    public void moveUnitCall() {
        assertTrue(gameProxy.moveUnit(null, null));
    }

    @Test
    public void endOfturnCall(){
        gameProxy.endOfTurn();
        assertTrue(servant.changeMe);
    }

    @Test
    public void changeWorkForceFocusInCityAtCall(){
        gameProxy.changeWorkForceFocusInCityAt(null, null);
        assertTrue(servant.changeMe);
    }

    @Test
    public void changeProductionInCityAtCall(){
        gameProxy.changeProductionInCityAt(null, null);
        assertTrue(servant.changeMe);
    }

    @Test
    public void addObserver() {
        GameObserver observer = new NullObserver();
        gameProxy.addObserver(observer);
        assertTrue(servant.changeMe);
    }

    @Test
    public void performUnitAction() {
        Position pos = new Position(2, 1);
        gameProxy.performUnitActionAt(pos);
        assertEquals(servant.unitActionPerformedAt, pos);
    }

    @Test
    public void setTileFocus() {
        gameProxy.setTileFocus(null);
        assertTrue(servant.changeMe);
    }

    @Test
    public void getOwnerCity(){
        assertThat(cityProxy.getOwner(), is(Player.YELLOW));
    }


    @Test
    public void getSizeCity(){
        assertThat(cityProxy.getSize(), is(100));
    }

    @Test
    public void getTreasuryCity(){
        assertThat(cityProxy.getTreasury(), is(100));
    }

    @Test
    public void getProductionCity(){
        assertEquals(cityProxy.getProduction(), "Women");
    }

    @Test
    public void getWorkForceFocus(){
        assertEquals(cityProxy.getWorkforceFocus(), "Women");
    }

    @Test
    public void getTypeStringTile(){
        assertEquals(tileProxy.getTypeString(), "Heaven");
    }

    @Test
    public void getTypeStringUnit(){
        assertEquals(unitProxy.getTypeString(), "AndersAnd");
    }

    @Test
    public void getOwnerUnit(){
        assertEquals(unitProxy.getOwner(), Player.GREEN);
    }

    @Test
    public void getMovecountUnit(){
        assertEquals(unitProxy.getMoveCount(), 100);
    }

    @Test
    public void getDefensiveStrengthUnit(){
        assertEquals(unitProxy.getMoveCount(), 100);
    }

    @Test
    public void getAttackingStrengthUnit(){
        assertEquals(unitProxy.getAttackingStrength(), 100);
    }
}

