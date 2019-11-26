package hotciv.broker;

import hotciv.framework.*;

public class Servant {
    private Game game;

    public Servant(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
