package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

public class CityImpl implements City {
    private int size;
    private int treasury;
    private Player owner;
    private String production;
    private String workforceFocus;
    private int productionValue;
    private int productionCost;
    private int food;
    private Position location;

    public CityImpl(Player owner, Position loc) {
        this(1, 0, owner, GameConstants.ARCHER, GameConstants.foodFocus, loc);
    }

    public CityImpl(int size, int treasury, Player owner, String production, String workforceFocus, Position loc) {
        this.size = size;
        this.treasury = treasury;
        this.owner = owner;
        this.workforceFocus = workforceFocus;
        productionValue = 0;
        setProduction(production);
        this.location = loc;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getTreasury() {
        return treasury;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
        productionCost = GameConstants.UNITS.toClass(production).getCost();
    }

    public int getProductionCost() {
        return productionCost;
    }

    @Override
    public String getWorkforceFocus() {
        return workforceFocus;
    }

    public int getProductionValue(){
        return productionValue;
    }

    public void addProductionValue(int value){
        productionValue += value;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getFood() {
        return food;
    }

    public void resetFood() {
        this.food = 0;
    }

    public Position getLocation() {
        return location;
    }

    public void increaseSize(){
        if (size >= 9 )
            return;
        size += 1;
    }

    public void addFood(int food) {
        this.food += food;
    }

    public void setWorkforceFocus(String workforceFocus) {
        this.workforceFocus = workforceFocus;
    }

    public boolean decrementSize() {
        if(size == 1)
            return false;
        size -= 1;
        return true;
    }
}
