package hotciv.framework.unitAction;
import hotciv.framework.*;

public class GammaAction implements UnitActionStrategy {
    public boolean doAction(Unit unit) {
        switch (unit.getTypeString()) {
            case GameConstants.SETTLER:
            case GameConstants.ARCHER:
            default:
                return true;
        }
    }
}
