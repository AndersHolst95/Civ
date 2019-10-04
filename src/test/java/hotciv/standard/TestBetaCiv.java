package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.factory.BetaFactory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestBetaCiv {
    private GameImpl game;

    private void endRound(){
        game.endOfTurn();
        game.endOfTurn();
    }
    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new BetaFactory());
    }

    @Test
    public void betaCivWorldAgeCond1() {
        int worldage = game.getAge();
        endRound();
        assertThat(game.getAge(), is(worldage +100));
        for (int i = 1; i< 10; i++){endRound();}
        assertThat(game.getAge(), is(worldage +1000));
    }

    @Test
    public void betaCivWorldAgeCond2() {
        for (int i = 0; i< 39; i++){
            endRound();
        }
        assertThat(game.getAge(), is(-100));
        endRound();
        assertThat(game.getAge(), is(-1));
        endRound();
        assertThat(game.getAge(), is(1));
        endRound();
        assertThat(game.getAge(), is(50));
    }

    @Test
    public void betaCivWorldAgeCond3(){
        for (int i = 0; i < 42; i++) {
            endRound();
        }
        assertThat(game.getAge(), is(50));
        endRound();
        assertThat(game.getAge(), is(100));
        for (int i = 0; i < 32; i++) {
            endRound();
        }
        assertThat(game.getAge(), is(1700));
        endRound();
        assertThat(game.getAge(), is(1750));
    }

    @Test
    public void betaCivWorldAgeCond4(){
        for (int i = 0; i < 76; i++) {
            endRound();
        }
        assertThat(game.getAge(), is(1750));
        endRound();
        assertThat(game.getAge(), is(1775));
        for (int i = 0; i < 5; i++) {
            endRound();
        }
        assertThat(game.getAge(), is(1900));
    }

    @Test
    public void betaCivWorldAgeCond5(){
        for (int i = 0; i < 82; i++) {
            endRound();
        }
        assertThat(game.getAge(), is(1900));
        endRound();
        assertThat(game.getAge(), is(1905));
        for (int i = 0; i < 13; i++) {
            endRound();
        }
        assertThat(game.getAge(), is(1970));
    }

    @Test
    public void betaCivWorldAgeCond6(){
        for (int i = 0; i < 96; i++) {
            endRound();
        }
        assertThat(game.getAge(), is(1970));
        endRound();
        assertThat(game.getAge(), is(1971));
        for (int i = 0; i < 48; i++) {
            endRound();
        }
        assertThat(game.getAge(), is(2019));
    }

    @Test
    public void betaConquestVictoryRed(){
        ((CityImpl) game.getCityAt(new Position(4,1))).setOwner(Player.RED);
        endRound();
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void betaConquestVictoryBlue(){
        ((CityImpl) game.getCityAt(new Position(1,1))).setOwner(Player.BLUE);
        endRound();
        assertThat(game.getWinner(), is(Player.BLUE));
    }

}