package hotciv.standard;

import hotciv.framework.*;

import hotciv.framework.age.ConstantAging;
import hotciv.framework.layout.StandardLayout;
import hotciv.framework.random.DeterministicDieRoll;
import hotciv.framework.resolveAttack.ActualCombat;
import hotciv.framework.resolveAttack.AttackerWins;
import hotciv.framework.unitAction.GammaAction;
import hotciv.framework.unitAction.NoAction;
import hotciv.framework.victoryStrategy.TimeVictory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;


public class TestEpsilonCiv {
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
        game = new GameImpl(new ConstantAging(), new TimeVictory(), new GammaAction(), new StandardLayout(),
                new ActualCombat(new DeterministicDieRoll()));;
    }

}
