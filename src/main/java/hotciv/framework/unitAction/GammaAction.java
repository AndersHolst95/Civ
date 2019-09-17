package hotciv.framework.unitAction;
import hotciv.framework.*;
import hotciv.standard.*;

public class GammaAction implements UnitActionStrategy {
    public boolean doAction(Position pos) {
        switch (World.getUnitAt(pos).getTypeString()) {
            case GameConstants.SETTLER:
                World.setCityAt(pos, new CityImpl(World.getUnitAt(pos).getOwner()));
                World.removeUnit(pos);
                return true;
            case GameConstants.ARCHER:
                ((UnitImpl) World.getUnitAt(pos)).toggleFortify();
            default:
                return false;
        }
    }
}
