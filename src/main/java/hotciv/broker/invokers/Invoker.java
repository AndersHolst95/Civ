package hotciv.broker.invokers;

import frds.broker.ReplyObject;
import hotciv.framework.Game;

public class Invoker implements frds.broker.Invoker {
    Game servant;

    public Invoker(Game servant) {
        this.servant = servant;
    }

    public Game getServant() {
        return servant;
    }

    public ReplyObject handleRequest(String objectId, String operationName, String payload) {
        return null;
    }
}
