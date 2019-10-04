package hotciv.standard;


import hotciv.framework.*;

import hotciv.standard.factory.GammaFactory;
import hotciv.standard.factory.ThetaFactory;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestThetaCiv {
    private GameImpl game;

    private void endRound() {
        game.endOfTurn();
        game.endOfTurn();
    }

    /**
     * Fixture for gammaCiv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new ThetaFactory());
    }

    @Test
    public void citiesCanProduceBombers() {
        Position pos = new Position(1, 1);
        CityImpl city = (CityImpl) World.getCityAt(pos);
        city.addProductionValue(60);
        game.setProduction(GameConstants.B52, city);
        endRound();
        assertThat(World.getUnitAt(pos).getTypeString(), is(GameConstants.B52));
    }

}