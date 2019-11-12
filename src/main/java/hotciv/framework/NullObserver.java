package hotciv.framework;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class NullObserver implements GameObserver {
    @Override
    public void worldChangedAt(Position pos) {

    }

    @Override
    public void turnEnds(Player nextPlayer) {

    }

    @Override
    public void tileFocusChangedAt(Position position) {

    }
}
