package hotciv.framework.unitAction;
import hotciv.framework.*;
import hotciv.standard.UnitImpl;

public interface UnitAction {

    boolean doAction(Unit unit, Position pos);
}

