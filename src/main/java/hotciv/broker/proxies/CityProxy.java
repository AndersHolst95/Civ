package hotciv.broker.proxies;

import frds.broker.Requestor;
import hotciv.broker.OperationNames;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.standard.TileImpl;

public class CityProxy {
    private Requestor requestor;

    public CityProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    public Player getOwner(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getOwnerCity, Player.class);
    }

    public int getSize(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getSize, Integer.class);
    }

    public int getTreasury(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getTreasury, Integer.class);
    }

    public String getProduction(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getProduction, String.class);
    }

    public String getWorkforceFocus(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getWorkforceFocus, String.class);
    }
}
