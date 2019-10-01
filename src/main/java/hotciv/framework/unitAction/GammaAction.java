package hotciv.framework.unitAction;
import hotciv.framework.*;
import hotciv.standard.*;

public class GammaAction implements UnitActionStrategy {
    public boolean doAction(Position pos) {
        switch (World.getUnitAt(pos).getTypeString()) {
            case GameConstants.SETTLER:
                World.setCityAt(new CityImpl(World.getUnitAt(pos).getOwner(), pos));
                World.removeUnit(pos);
                return true;
            case GameConstants.ARCHER:
                ((UnitImpl) World.getUnitAt(pos)).toggleFortify();
            default:
                return false;
        }
    }
}
