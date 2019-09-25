package hotciv.standard;

import hotciv.framework.*;

import hotciv.framework.age.ConstantAging;
import hotciv.framework.layout.StandardLayout;
import hotciv.framework.random.DeterministicDieRoll;
import hotciv.framework.resolveAttack.ActualCombat;
import hotciv.framework.unitAction.GammaAction;
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

    @Test
    public void blueDefendingArcherWins() {
        Position pos1 = new Position(7, 7); // plains
        Position pos2 = new Position(7, 8); // also plains
        game.setUnitAt(pos1, new UnitImpl(GameConstants.ARCHER, Player.RED));
        game.setUnitAt(pos2, new UnitImpl(GameConstants.ARCHER, Player.BLUE));
        assertFalse(game.moveUnit(pos1, pos2)); // assert that the blue archer wins
    }

    @Test
    public void blueDefendingArcherWinsWithCityBonus() {
        Position pos1 = new Position(7, 7); // plains
        Position pos2 = new Position(7, 8); // also plains
        game.setCityAt(pos2, new CityImpl(Player.BLUE));
        game.setUnitAt(pos1, new UnitImpl(GameConstants.LEGION, Player.RED));
        game.setUnitAt(pos2, new UnitImpl(GameConstants.ARCHER, Player.BLUE));
        assertFalse(game.moveUnit(pos1, pos2)); // assert that the blue archer wins
    }

    @Test
    public void redAttackingLegionWinsWithTerrainBonus() {
        Position pos1 = new Position(7, 7); // plains
        Position pos2 = new Position(7, 8); // also plains
        game.setTypeAt(pos1, GameConstants.HILLS);
        game.setUnitAt(pos1, new UnitImpl(GameConstants.LEGION, Player.RED));
        game.setUnitAt(pos2, new UnitImpl(GameConstants.ARCHER, Player.BLUE));
        assertTrue(game.moveUnit(pos1, pos2)); // assert that the blue archer wins
    }

    @Test
    public void redAttackingArcherWinsWithFriendlySupport() {
        Position attacker = new Position(7, 7); // plains
        Position enemy = new Position(7, 8); // also plains
        Position friendly1 = new Position(7, 6); // plains..
        Position friendly2 = new Position(6, 7); // plains..
        Position friendly3 = new Position(6, 6); // plains..
        game.setUnitAt(attacker, new UnitImpl(GameConstants.ARCHER, Player.RED));
        game.setUnitAt(friendly1, new UnitImpl(GameConstants.ARCHER, Player.RED));
        game.setUnitAt(friendly2, new UnitImpl(GameConstants.ARCHER, Player.RED));
        game.setUnitAt(friendly3, new UnitImpl(GameConstants.ARCHER, Player.RED));
        game.setUnitAt(enemy, new UnitImpl(GameConstants.LEGION, Player.BLUE));
        assertTrue(game.moveUnit(attacker, enemy)); // assert that the blue archer wins
    }
}
