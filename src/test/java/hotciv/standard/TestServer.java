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
        assertEquals(19, client.gameProxy.getAge());

        // Check playerInTurn call
        assertEquals(Player.GREEN, client.gameProxy.getPlayerInTurn());

        // Check moveUnit call
        Position pos = new Position(2, 2);
        client.gameProxy.moveUnit(null, pos);

        // Check getUnitAt call
        assertNotNull(client.gameProxy.getUnitAt(pos));
    }

    @Test
    public void tt(){
        // Check getAge call
        assertEquals(19, client.gameProxy.getAge());
    }
    @Test
    public void ttt(){
        // Check getAge call
        assertEquals(19, client.gameProxy.getAge());
    }

    @Test
    public void tttt(){
        // Check getAge call
        assertEquals(19, client.gameProxy.getAge());
    }

    @Test
    public void ttttt(){
        // Check getAge call
        assertEquals(19, client.gameProxy.getAge());
    }
}
