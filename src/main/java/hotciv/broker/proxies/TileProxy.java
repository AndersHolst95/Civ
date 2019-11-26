package hotciv.broker.proxies;

import frds.broker.Requestor;
import hotciv.broker.OperationNames;
import hotciv.framework.Tile;

public class TileProxy{
    private Requestor requestor;

    public TileProxy(Requestor requestor) {
        this.requestor = requestor;
    }
    public String getTypeString(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getTypeStringTile, String.class);
    }
}
