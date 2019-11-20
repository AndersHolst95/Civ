package hotciv.broker;

import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.broker.invokers.Invoker;
import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.standard.factory.SemiFactory;
import hotciv.stub.GameStub;
import hotciv.stub.ServerStub;


public class Server {
    SocketServerRequestHandler requestHandler;
    Invoker invoker;

    public Server(Game game, int port) {
        invoker = new Invoker(game);
        requestHandler = new SocketServerRequestHandler(port, invoker);
    }

    public void start() {
//        requestHandler.start();
        requestHandler.run();
    }

    public void stop() {
        requestHandler.stop();
    }

    public static void main(String[] args) {
        Server server = new Server(new GameStub(), 2800);
        server.start();
    }
}