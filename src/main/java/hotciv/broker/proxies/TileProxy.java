package hotciv.broker.proxies;

import frds.broker.Requestor;
import hotciv.broker.OperationNames;
import hotciv.framework.Tile;

public class TileProxy implements Tile {
    private Requestor requestor;
    private final String objectId = "lol";

    public TileProxy(Requestor requestor) {
        this.requestor = requestor;
    }
    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getTypeStringTile, String.class);
    }
}
