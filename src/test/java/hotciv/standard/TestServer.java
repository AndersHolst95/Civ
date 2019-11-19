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

    private ClientRequestHandler crh = new NetworkClientRequestHandler(ip);
    private Requestor requestor = new StandardJSONRequestor(crh);
    private ClientProxy client = new ClientProxy(requestor);

    @Test
    public void checkServerCalls() {
        // Check getAge call
        assertEquals(19, client.getAge());

        // Check playerInTurn call
        assertEquals(Player.GREEN, client.getPlayerInTurn());

        // Check moveUnit call
        Position pos = new Position(2, 2);
        client.moveUnit(null, pos);

        // Check getUnitAt call
        assertNotNull(client.getUnitAt(pos));
    }

    @Test
    public void tt(){
        // Check getAge call
        assertEquals(19, client.getAge());
    }
    @Test
    public void ttt(){
        // Check getAge call
        assertEquals(19, client.getAge());
    }

    @Test
    public void tttt(){
        // Check getAge call
        assertEquals(19, client.getAge());
    }

    @Test
    public void ttttt(){
        // Check getAge call
        assertEquals(19, client.getAge());
    }
}
