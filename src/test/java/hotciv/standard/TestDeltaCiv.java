package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.age.ConstantAging;
import hotciv.framework.layout.DeltaLayout;
import hotciv.framework.layout.StandardLayout;
import hotciv.framework.resolveAttack.AttackerWins;
import hotciv.framework.unitAction.NoAction;
import hotciv.framework.victoryStrategy.TimeVictory;
import hotciv.standard.factory.DeltaFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestDeltaCiv {
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
        game = new GameImpl(new DeltaFactory());
    }

    @Test
    public void deltaCustomMap() {
        String[][] layout = new String[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        layout[2][2] = "o"; // places an ocean at (2, 2), rest is plains by default
        game = new GameImpl(new DeltaFactory(), layout);
        assertThat(game.getTileAt(new Position(2, 2)).getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.getTileAt(new Position(12, 7)).getTypeString(), is(GameConstants.PLAINS)); // random spot
    }

    @Test
    public void deltaMountainsAt26() {
        assertThat(game.getTileAt(new Position(2, 6)).getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void deltaOceansAt42() {
        assertThat(game.getTileAt(new Position(4, 2)).getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void deltaOceansAt1313() {
        assertThat(game.getTileAt(new Position(13, 13)).getTypeString(), is(GameConstants.OCEANS));
    }
}
