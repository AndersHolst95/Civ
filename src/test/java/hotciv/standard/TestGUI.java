package hotciv.standard;


import hotciv.standard.factory.SemiFactory;
import org.junit.Before;
import org.junit.Test;
import hotciv.framework.*;
import hotciv.standard.factory.AlphaFactory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestGUI {
    private GameImpl game;

    private void endRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    @Before
    public void setUp() {
        game = new GameImpl(new SemiFactory());
    }

    public class TestObserver implements GameObserver{
        Position latestPos;
        Player latestPlayer;

        public void worldChangedAt(Position pos) {latestPos = pos; }
        public void turnEnds(Player nextPlayer) {latestPlayer = nextPlayer;}
        public void tileFocusChangedAt(Position position) {latestPos = position;}

        @Override
        public void requestUpdate() {

        }

        public Player getLatestPlayer() {return latestPlayer;}
        public Position getLatestPos() {return latestPos;}
    }

    @Test
    public void testObserverNotifications(){
        TestObserver testObserver = new TestObserver();
        game.addObserver(testObserver);
        Position pos1 = new Position(0, 6);
        Position pos2 = new Position(0, 7);
        Position cityPos = new Position(1, 6);

        game.setUnitAt(pos1, game.createUnit(GameConstants.ARCHER, GameVariables.currentPlayer));
        assertThat(testObserver.getLatestPos(), is(pos1));

        game.moveUnit(pos1, pos2);
        assertThat(testObserver.getLatestPos(), is(pos2));

        game.setCityAt(new CityImpl(Player.RED, cityPos));
        assertThat(testObserver.getLatestPos(), is(cityPos));

        game.endOfTurn();
        assertThat(testObserver.getLatestPlayer(), is(Player.BLUE));
    }



}
