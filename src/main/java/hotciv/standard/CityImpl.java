package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

public class CityImpl implements City {
    private int size;
    private int treasury;
    private Player owner;
    private String production;
    private String workforceFocus;

    public CityImpl(int size, int treasury, Player owner, String production, String workforceFocus) {
        this.size = size;
        this.treasury = treasury;
        this.owner = owner;
        this.production = production;
        this.workforceFocus = workforceFocus;
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

    @Override
    public String getWorkforceFocus() {
        return workforceFocus;
    }
}
