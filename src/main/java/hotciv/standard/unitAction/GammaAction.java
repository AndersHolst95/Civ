package hotciv.standard.unitAction;
import hotciv.framework.*;
import hotciv.standard.*;

public class GammaAction implements UnitActionStrategy {
    public void doAction(Position pos) {
        switch (World.getUnitAt(pos).getTypeString()) {
            case GameConstants.SETTLER:
                World.setCityAt(new CityImpl(World.getUnitAt(pos).getOwner(), pos));
                World.removeUnit(pos);
                break;
            case GameConstants.ARCHER:
                ((UnitImpl) World.getUnitAt(pos)).toggleFortify();
                break;
            default:
                break;
        }
    }
}
