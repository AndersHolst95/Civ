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

        gameProxy.getCityAt(null);
        gameProxy.getUnitAt(null);
        gameProxy.getTileAt(null);
    }


    @Test
    public void getAgeCall() {
        assertEquals(16, gameProxy.getAge());
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
        assertThat(cityProxy.getOwner("city"), is(Player.YELLOW));
    }


    @Test
    public void getSizeCity(){
        assertThat(cityProxy.getSize("city"), is(82));
    }

    @Test
    public void getTreasuryCity(){
        assertThat(cityProxy.getTreasury("city"), is(119));
    }

    @Test
    public void getProductionCity(){
        assertEquals(cityProxy.getProduction("city"), "dragon");
    }

    @Test
    public void getWorkForceFocus(){
        assertEquals(cityProxy.getWorkforceFocus("city"), "vandland");
    }

    @Test
    public void getTypeStringTile(){
        assertEquals(tileProxy.getTypeString("tile"), "oasis");
    }

    @Test
    public void getTypeStringUnit(){
        assertEquals(unitProxy.getTypeString("unit"), "BigBoy");
    }

    @Test
    public void getOwnerUnit(){
        assertEquals(unitProxy.getOwner("unit"), Player.GREEN);
    }

    @Test
    public void getMovecountUnit(){
        assertEquals(unitProxy.getMoveCount("unit"), 9);
    }

    @Test
    public void getDefensiveStrengthUnit(){
        assertEquals(unitProxy.getDefensiveStrength("unit"), 64);
    }

    @Test
    public void getAttackingStrengthUnit(){
        assertEquals(unitProxy.getAttackingStrength("unit"), 1337);
    }
}

