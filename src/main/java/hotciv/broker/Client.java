package hotciv.broker;

import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.broker.proxies.*;

public class Client {
    private SocketClientRequestHandler requestHandler;
    private Requestor requestor;
    public GameProxy gameProxy;
    public CityProxy cityProxy;
    public UnitProxy unitProxy;
    public TileProxy tileProxy;

    public Client(String host, int port) {
        requestHandler = new SocketClientRequestHandler(host, port);
        requestor = new StandardJSONRequestor(requestHandler);
        gameProxy = new GameProxy(requestor);
        cityProxy = new CityProxy(requestor);
        unitProxy = new UnitProxy(requestor);
        tileProxy = new TileProxy(requestor);
    }

    public GameProxy getGameProxy() {
        return gameProxy;
    }
}
