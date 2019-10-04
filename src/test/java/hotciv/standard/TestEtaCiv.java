package hotciv.standard;

import hotciv.framework.*;

import hotciv.standard.factory.EtaFactory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestEtaCiv {
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
        game = new GameImpl(new EtaFactory());
    }

    @Test
    public void cityCanGrow() {
        for (int i = 0; i < 40; i++) {
            endRound();
        }
        assertTrue(World.getCityAt(new Position(1, 1)).getSize() > 1);
    }

    @Test
    public void foodFocusGivesCorrectFood() {
        CityImpl city = (CityImpl) World.getCityAt(new Position(1, 1));
        city.increaseSize();
        city.increaseSize();
        endRound();
        assertThat(city.getFood(), is(7)); // city + plains + plains
    }

    @Test
    public void productionFocusGivesCorrectProduction() {
        CityImpl city = (CityImpl) World.getCityAt(new Position(1, 1));
        city.setWorkforceFocus(GameConstants.productionFocus);
        city.increaseSize();
        city.increaseSize();
        endRound();
        assertThat(city.getProductionValue(), is(4)); // city + hill + mountain
    }

    @Test
    public void checkThatSize9IsMax() {
        CityImpl city = (CityImpl) World.getCityAt(new Position(1, 1));
        for (int i = 0; i < 10; i++) {
            city.increaseSize();
        }
        assertThat(city.getSize(), is(9));
    }




}
