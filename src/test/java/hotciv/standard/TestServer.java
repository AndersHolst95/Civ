package hotciv.standard;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.broker.*;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.*;
import static org.junit.Assert.*;

public class TestServer {
    String ip = "10.192.141.139";
    Client client;

    @Before
    public void setup() {
        client = new Client(ip, 2800);
    }

    @Test
    public void checkServerCalls() {
        // Check getAge call
        assertEquals(16, client.gameProxy.getAge());

        // Check playerInTurn call
        assertEquals(Player.YELLOW, client.gameProxy.getPlayerInTurn());

        // Check moveUnit call
        Position pos = new Position(2, 2);
        client.gameProxy.moveUnit(null, pos);

        // Check getUnitAt call
        assertNotNull(client.gameProxy.getUnitAt(pos));
    }

    @Test
    public void game1(){
        // Check getAge call
        assertEquals(16, client.gameProxy.getAge());
    }
    @Test
    public void game2(){
        // Check getAge call
        assertEquals(16, client.gameProxy.getAge());
    }

    @Test
    public void game3(){
        // Check getAge call
        assertEquals(16, client.gameProxy.getAge());
    }

    @Test
    public void game4(){
        // Check getAge call
        assertEquals(16, client.gameProxy.getAge());
    }

    @Test
    public void city1(){
        client.gameProxy.getCityAt(null);
        assertEquals(client.cityProxy.getProduction("city"), "dragon");
        assertEquals(client.cityProxy.getOwner("city"), Player.YELLOW);
        assertEquals(client.cityProxy.getWorkforceFocus("city"), "vandland");
        assertEquals(client.cityProxy.getTreasury("city"), 119);
        assertEquals(client.cityProxy.getSize("city"), 82);
    }

    @Test
    public void unit1(){
        assertEquals(client.unitProxy.getMoveCount(), 9);
        assertEquals(client.unitProxy.getAttackingStrength(), 1337);
        assertEquals(client.unitProxy.getDefensiveStrength(), 64);
        assertEquals(client.unitProxy.getTypeString(), "BigBoy");
        assertEquals(client.unitProxy.getOwner(), Player.GREEN);
    }

    @Test
    public void tile1(){
        client.gameProxy.getTileAt(null);
        assertEquals("oasis", client.tileProxy.getTypeString("tile"));
    }
}
