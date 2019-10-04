package hotciv.standard.unitAction;
import hotciv.framework.*;

public class NoAction implements UnitActionStrategy {
    public boolean doAction(Position pos) {
        return true;
    }
}
