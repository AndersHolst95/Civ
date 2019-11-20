package hotciv.broker.proxies;

import frds.broker.Requestor;
import hotciv.broker.OperationNames;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitProxy {
    private Requestor requestor;

    public UnitProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    public String getTypeString(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getTypeStringUnit, String.class);
    }

    public Player getOwner(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getOwnerUnit, Player.class);
    }

    public int getMoveCount(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getMoveCount, Integer.class);
    }

    public int getDefensiveStrength(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getDefensiveStrength, Integer.class);
    }

    public int getAttackingStrength(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getAttackingStrength, Integer.class);
    }
}
