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
    String ip = "localhost";
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
        client.cityProxy.getProduction("1");
        client.cityProxy.getOwner("1");
        client.cityProxy.getWorkforceFocus("1");
        client.cityProxy.getTreasury("1");
        client.cityProxy.getSize("1");
    }

    @Test
    public void unit1(){
        client.unitProxy.getMoveCount();
        client.unitProxy.getAttackingStrength();
        client.unitProxy.getDefensiveStrength();
        client.unitProxy.getTypeString();
        client.unitProxy.getOwner();
    }

   // @Test
    //public void tile1(){
    //    client.gameProxy.getTileAt(null);
    //    assertEquals("oasis", client.tileProxy.getTypeString("tile"));
    //}
}
