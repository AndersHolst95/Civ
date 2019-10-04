package hotciv.standard.unitMovementDistinction;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;

public interface UnitMovementDistinctionStrategy {
    boolean validUnitPosition(Position pos, String type);
    default boolean validPosition(Position pos) {
        // Check for null-position
        if(pos == null)
            return false;

        // check for out-of-bounds
        if (pos.getColumn() < 0 || GameConstants.WORLDSIZE < pos.getColumn())
            return false;
        if (pos.getRow() < 0 || GameConstants.WORLDSIZE < pos.getRow())
            return false;

        return true;
    }
}

