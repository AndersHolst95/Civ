package hotciv.standard.unitMovementDistinction;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.World;

public class GroundAndAir implements UnitMovementDistinctionStrategy {
    public boolean validUnitPosition(Position pos) {
        // check if the position is null and within the world border
        if (!validPosition(pos))
            return false;

        // if the unit is flying, we do not check for impassable terrain
        if (GameConstants.UNITS.toClass(World.getUnitAt(pos).getTypeString()).getMovementCategory().equals(GameConstants.UNITS.FLYING))
            return true;

        // check for mountains and ocean
        if (World.getTileAt(pos).getTypeString().equals(GameConstants.MOUNTAINS) || World.getTileAt(pos).getTypeString().equals(GameConstants.OCEANS))
            return false;

        return true;
    }
}
