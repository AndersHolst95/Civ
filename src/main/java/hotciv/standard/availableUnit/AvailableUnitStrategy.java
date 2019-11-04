package hotciv.standard.availableUnit;

import java.util.ArrayList;

public interface AvailableUnitStrategy {
    boolean validUnitType(String type);
    ArrayList<String> getAvailableUnits();
}


