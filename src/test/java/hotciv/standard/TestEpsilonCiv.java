package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.random.RandomStrategy;
import hotciv.standard.factory.EpsilonFactory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestEpsilonCiv {
    private GameImpl game;

    // Properly encapsulated test stub for removing randomness from dieRoll
    public static class DeterministicDieRoll implements RandomStrategy {
        int[] intList = new int[] {1,2,3,4,5,6};
        int index = 0;

        @Override
        public int getNext() {
            int n = intList[Math.floorMod(index,6)];
            index ++;
            return n;
        }
    }

    private void endRound(){
        game.endOfTurn();
        game.endOfTurn();
    }
    /**
     * Fixture for gammaCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonFactory(new DeterministicDieRoll()));
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
        game.setCityAt(new CityImpl(Player.BLUE, pos2));
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

    @Test
    public void testVictoryWhenWinningThreeCombats(){
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

        // Set cities at reds units
        game.setCityAt(new CityImpl(Player.RED, red1));
        game.setCityAt(new CityImpl(Player.RED, red2));
        game.setCityAt(new CityImpl(Player.RED, red3));

        // Do combat
        game.moveUnit(red1, blue1);
        game.moveUnit(red2, blue2);
        game.moveUnit(red3, blue3);

        assertNull(game.getWinner());
        endRound();
        assertThat(game.getWinner(), is(Player.RED));
    }
}
