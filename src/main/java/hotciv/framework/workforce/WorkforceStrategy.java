package hotciv.framework.workforce;


import hotciv.framework.Position;
import hotciv.standard.CityImpl;

public interface WorkforceStrategy {
    void workTiles(CityImpl city);
    void growCity(CityImpl city);
}

