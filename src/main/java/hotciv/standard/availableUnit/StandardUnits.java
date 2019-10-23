package hotciv.standard.availableUnit;

import hotciv.framework.GameConstants;

import java.util.ArrayList;

public class StandardUnits implements AvailableUnitStrategy{
    ArrayList<String> validTypes = new ArrayList<String>(){
        {
            add(GameConstants.UNITS.ARCHER.string);
            add(GameConstants.UNITS.LEGION.string);
            add(GameConstants.UNITS.SETTLER.string);
        }
    };

    public boolean validUnitType(String type) {
        return validTypes.contains(type);
    }
}
