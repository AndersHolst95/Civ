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
import hotciv.stub.GameStub;
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
        assertThat(cityProxy.getOwner("1"), is(Player.YELLOW));
    }


    @Test
    public void getSizeCity(){
        assertThat(cityProxy.getSize("1"), is(100));
    }

    @Test
    public void getTreasuryCity(){
        assertThat(cityProxy.getTreasury("1"), is(100));
    }

    @Test
    public void getProductionCity(){
        assertEquals(cityProxy.getProduction("1"), "Women");
    }

    @Test
    public void getWorkForceFocus(){
        assertEquals(cityProxy.getWorkforceFocus("1"), "Women");
    }

    @Test
    public void getTypeStringTile(){
        assertEquals(tileProxy.getTypeString("1"), "Heaven");
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

