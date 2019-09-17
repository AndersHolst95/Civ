package hotciv.framework.unitAction;
import hotciv.framework.*;

public class NoAction implements UnitActionStrategy {
    public boolean doAction(Unit unit) {
        return true;
    }
}
