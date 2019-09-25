package hotciv.standard;

import hotciv.framework.*;

import hotciv.framework.age.ConstantAging;
import hotciv.framework.layout.StandardLayout;
import hotciv.framework.resolveAttack.AttackerWins;
import hotciv.framework.unitAction.GammaAction;
import hotciv.framework.unitAction.NoAction;
import hotciv.framework.victoryStrategy.TimeVictory;
import hotciv.framework.victoryStrategy.ZetaVictory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;


public class TestZetaCiv {
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
        game = new GameImpl(new ConstantAging(), new ZetaVictory(), new GammaAction(), new StandardLayout(), new AttackerWins());;
    }

    @Test
    public void testConquestVictoryBeforeRound20(){
        ((CityImpl) game.getCityAt(new Position(4,1))).setOwner(Player.RED);
        endRound();
        assertThat(game.getWinner(), is(Player.RED));

    }

    @Test
    public void testThreeCombatVictoriesAfterRound20(){
        // Perform combat before round 20
        Position red = new Position(0,7);
        Position blue = new Position(0,6);
        game.setUnitAt(red, new UnitImpl(GameConstants.LEGION, Player.RED));
        game.setUnitAt(blue, new UnitImpl(GameConstants.LEGION, Player.BLUE));
        game.moveUnit(red, blue);

        for (int i = 0; i < 20 ; i++) {
            endRound();
        }

        Position red1 = new Position(7,7);
        Position red2 = new Position(8,7);
        Position red3 = new Position(9,7);
        Position blue1 = new Position(7, 8);
        Position blue2 = new Position(8, 8);
        Position blue3 = new Position(9, 8);

        // Create red units
        game.setUnitAt(red1, new UnitImpl(GameConstants.LEGION, Player.RED));
        game.setUnitAt(red2, new UnitImpl(GameConstants.LEGION, Player.RED));
        game.setUnitAt(red3, new UnitImpl(GameConstants.LEGION, Player.RED));

        // Create blue units
        game.setUnitAt(blue1, new UnitImpl(GameConstants.LEGION, Player.BLUE));
        game.setUnitAt(blue2, new UnitImpl(GameConstants.LEGION, Player.BLUE));
        game.setUnitAt(blue3, new UnitImpl(GameConstants.LEGION, Player.BLUE));

        // Do combat
        game.moveUnit(red1, blue1);
        endRound();
        assertNull(game.getWinner());
        game.moveUnit(red2, blue2);
        endRound();
        assertNull(game.getWinner());
        game.moveUnit(red3, blue3);
        endRound();
        assertThat(game.getWinner(), is(Player.RED));
    }
}
