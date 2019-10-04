package hotciv.standard.availableUnit;


import hotciv.framework.GameConstants;

import java.util.ArrayList;

public class B52Expansion implements AvailableUnitStrategy{
    ArrayList<String> validTypes = new ArrayList<>(){
        {
            add(GameConstants.UNITS.ARCHER.string);
            add(GameConstants.UNITS.LEGION.string);
            add(GameConstants.UNITS.SETTLER.string);
            add(GameConstants.UNITS.B52.string);
        }
    };

    public boolean validUnitType(String type) {
        return validTypes.contains(type);
    }
}
