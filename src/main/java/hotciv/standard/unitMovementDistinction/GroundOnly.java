package hotciv.standard.unitMovementDistinction;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.World;

public class GroundOnly implements UnitMovementDistinctionStrategy {
    public boolean validUnitPosition(Position pos) {
        // check if the position is null and within the world border
        if (!validPosition(pos))
            return false;

        // check for mountains and ocean
        if (World.getTileAt(pos).getTypeString().equals(GameConstants.MOUNTAINS) || World.getTileAt(pos).getTypeString().equals(GameConstants.OCEANS))
            return false;

        return true;
    }
}
