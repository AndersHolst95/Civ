package hotciv.standard;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.broker.*;
import hotciv.framework.*;
import hotciv.framework.*;
import hotciv.standard.factory.AlphaFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;



public class TestServer {
    ClientProxy client;

    @Before
    public void setup() {
        ClientRequestHandler crh = new NetworkClientRequestHandler();
        Requestor requestor = new StandardJSONRequestor(crh);
        client = new ClientProxy(requestor);
    }

    @Test
    public void callGetAge() {
        assertEquals(19, client.getAge());
    }

    @Test
    public void callGetPlayerInTurn() {
        assertEquals(Player.GREEN, client.getPlayerInTurn());
    }

    @Test
    public void callMoveAndGetUnit() {
        Position pos = new Position(2, 2);
        client.moveUnit(null, pos);
        //assertNotNull(client.getUnitAt(pos));
    }
}
