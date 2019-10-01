package hotciv.framework.workforce;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.World;
import hotciv.standard.CityImpl;

public class NoWorkableTiles implements WorkforceStrategy{
    public void workTiles(CityImpl city) {
        city.addProductionValue(GameConstants.CITY_PRODUCTION_PER_TURN); // add constant production
    }

    public void growCity(CityImpl city) { }
}
