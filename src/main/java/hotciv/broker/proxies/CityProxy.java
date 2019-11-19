package hotciv.broker.proxies;

import frds.broker.Requestor;
import hotciv.broker.OperationNames;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.standard.TileImpl;

public class CityProxy implements City {
    private Requestor requestor;
    private final String objectId = "lol";

    public CityProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getOwnerCity, Player.class);
    }

    public int getSize() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getSize, Integer.class);
    }

    public int getTreasury() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getTreasury, Integer.class);
    }

    public String getProduction() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getProduction, String.class);
    }

    public String getWorkforceFocus() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getWorkforceFocus, String.class);
    }
}
