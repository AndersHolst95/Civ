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

    public void refreshMoveCount(String objectId) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.refreshMoveCount, void.class);
    }

    public void toggleFortify(String objectId) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.toggleFortify, void.class);
    }

    public void setUsedAction(String objectId, boolean usedAction) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.setUsedAction, void.class, usedAction);
    }

    public boolean getUsedAction(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getUsedAction, boolean.class);
    }

    public void setMoveCount(String objectId, int i) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.setMoveCount, void.class, i);
    }
}