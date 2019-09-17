package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class CityImpl implements City {
    private int size;
    private int treasury;
    private Player owner;
    private String production;
    private String workforceFocus;
    private int productionValue;
    private int productionCost;

    public CityImpl(int size, int treasury, Player owner, String production, String workforceFocus) {
        this.size = size;
        this.treasury = treasury;
        this.owner = owner;
        this.workforceFocus = workforceFocus;
        productionValue = 0;
        setProduction(production);
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
}
