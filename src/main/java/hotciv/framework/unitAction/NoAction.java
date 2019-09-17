package hotciv.framework.unitAction;
import hotciv.framework.*;

public class NoAction implements UnitAction {
    public boolean doAction(Unit unit) {
        return true;
    }
}
