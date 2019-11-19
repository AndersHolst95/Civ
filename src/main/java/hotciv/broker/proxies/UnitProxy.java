package hotciv.broker.proxies;

import frds.broker.Requestor;
import hotciv.broker.OperationNames;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitProxy implements Unit {
    private Requestor requestor;
    private final String objectId = "lol";

    public UnitProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getTypeStringUnit, String.class);
    }

    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getOwnerUnit, Player.class);
    }

    public int getMoveCount() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getMoveCount, Integer.class);
    }

    public int getDefensiveStrength() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getDefensiveStrength, Integer.class);
    }

    public int getAttackingStrength() {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getAttackingStrength, Integer.class);
    }
}
