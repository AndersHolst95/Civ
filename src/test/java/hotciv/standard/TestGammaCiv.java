package hotciv.standard;

import hotciv.framework.*;

import hotciv.framework.age.ConstantAging;
import hotciv.framework.layout.StandardLayout;
import hotciv.framework.unitAction.GammaAction;
import hotciv.framework.unitAction.NoAction;
import hotciv.framework.victoryStrategy.TimeVictory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;


public class TestGammaCiv {
    private GameImpl game;

    private void endRound(){
        game.endOfTurn();
        game.endOfTurn();
    }
    /**
     * Fixture for gammaCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new ConstantAging(), new TimeVictory(), new GammaAction(), new StandardLayout());;
    }

    @Test
    public void gammaSettlerAction(){
        Position pos = new Position(4, 3); // a Settler is located at (4, 3)
        game.doUnitAction(pos);
        assertNull(game.getUnitAt(pos)); // check that the unit is gone
        assertNotNull(game.getCityAt(pos)); // check that a new city is created
        assertThat(game.getCityAt(pos).getSize(), is(1)); // verify the size is 1
    }

    @Test
    public void gammaArcherAction() {
        Position pos = new Position(2, 0); // an Archer is located at (2, 0)
        game.doUnitAction(pos);

        // check that the defensive value is doubled and that the unit cannot move
        assertThat(game.getUnitAt(pos).getDefensiveStrength(), is(2*GameConstants.UNITS.ARCHER.defStrength));
        assertFalse(game.moveUnit(pos, new Position(3, 0)));
        endRound();
        game.doUnitAction(pos);
        endRound();

        // check that its defensive value is normal and that it can move again
        assertThat(game.getUnitAt(pos).getDefensiveStrength(), is(GameConstants.UNITS.ARCHER.defStrength));
        assertTrue(game.moveUnit(pos, new Position(3, 0)));
    }
}
