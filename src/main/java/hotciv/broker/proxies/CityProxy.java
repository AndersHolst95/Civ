package hotciv.broker.proxies;

import frds.broker.Requestor;
import hotciv.broker.OperationNames;
import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;
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

    public int getFood(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getFood, Integer.class);
    }

    public Position getLocation(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getLocation, Position.class);
    }

    public int getProductionValue(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getProductionValue, Integer.class);
    }

    public int getProductionCost(String objectId) {
        return requestor.sendRequestAndAwaitReply(objectId, OperationNames.getProductionCost, Integer.class);
    }

    public void decrementSize(String objectId) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.decrementSize, Void.class);
    }

    public void addProductionValue(String objectId, int value) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.addProductionValue, Void.class, value);
    }

    public void setProduction(String objectId, String production) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.setProduction, Void.class, production);
    }

    public void setOwner(String objectId, Player owner) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.setOwner, Void.class, owner);
    }

    public void resetFood(String objectId) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.resetFood, Void.class);
    }

    public void increaseSize(String objectId) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.increaseSize, Void.class);
    }

    public void addFood(String objectId, int food) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.addFood, Void.class, food);
    }

    public void setWorkforceFocus(String objectId, String workforce) {
        requestor.sendRequestAndAwaitReply(objectId, OperationNames.setWorkforceFocus, Void.class, workforce);
    }
}
