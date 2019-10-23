package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.factory.FractalFactory;
import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestFractalLayout {
    private GameImpl game;

    private void endRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    /**
     * Fixture for deltaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new FractalFactory());
    }

    @Test
    public void integrationTest() {
        GameImpl tempGame;
        Position pos = new Position(0, 0); // the position being examined
        boolean foundDifferent = false;
        String firstType = game.getTileAt(pos).getTypeString();

        for (int i = 0; i < 1000; i++) {
            tempGame = new GameImpl(new FractalFactory());
            if (!firstType.equals(tempGame.getTileAt(pos).getTypeString()))
                foundDifferent = true;
        }
        assertTrue(foundDifferent);
    }
}
