package hotciv.framework.unitAction;
import hotciv.framework.*;
import hotciv.standard.CityImpl;

public class GammaAction implements UnitAction {

    public boolean doAction(Unit unit, Position pos){
        switch(unit.getTypeString()){
            case GameConstants.SETTLER:
            case GameConstants.ARCHER:
            default: return true;
        }
    }

}
