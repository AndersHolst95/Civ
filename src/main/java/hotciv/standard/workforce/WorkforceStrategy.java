package hotciv.standard.workforce;


import hotciv.standard.CityImpl;

public interface WorkforceStrategy {
    void workTiles(CityImpl city);
    void growCity(CityImpl city);
}

