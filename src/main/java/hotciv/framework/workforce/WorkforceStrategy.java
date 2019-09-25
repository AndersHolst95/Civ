package hotciv.framework.workforce;

import hotciv.framework.City;
import hotciv.framework.Position;

public interface WorkforceStrategy {
    void workTiles(Position pos);
    void growCity(City city);
}

