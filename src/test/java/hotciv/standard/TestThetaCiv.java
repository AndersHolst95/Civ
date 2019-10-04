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

    @Test
    public void bomberCanMove2in2Steps(){
        Position pos1 = new Position(7,7);
        Position pos2 = new Position(7,8);
        Position pos3 = new Position(7,9);
        game.setUnitAt(pos1, game.createUnit(GameConstants.B52, Player.RED));
        assertFalse(game.moveUnit(pos1,pos3));
        assertTrue(game.moveUnit(pos1, pos2));
        assertTrue(game.moveUnit(pos2,pos3));
    }

    @Test
    public void bomberCanMoveAtOcean(){
       Position pos1 = new Position(1,1);
       Position pos2 = new Position(1,0);
       game.setUnitAt(pos1, game.createUnit(GameConstants.B52, Player.RED));
       assertTrue(game.moveUnit(pos1, pos2));
    }

    @Test
    public void bumberCanMoveAtMountain(){
        Position pos1 = new Position(1,1);
        Position pos2 = new Position(2,2);
        game.setUnitAt(pos1, game.createUnit(GameConstants.B52, Player.RED));
        assertTrue(game.moveUnit(pos1, pos2));
    }

    @Test
    public void bomberCanDestroyCity(){
        Position pos1 = new Position(1,1);
        game.setUnitAt(pos1, game.createUnit(GameConstants.B52, Player.BLUE));
        assertNotNull(game.getCityAt(pos1));
        game.performUnitActionAt(pos1);
        assertNull(game.getCityAt(pos1));
    }

    @Test
    public void bomberCanKillCitizen(){
        Position pos1 = new Position(1,1);
        game.setUnitAt(pos1, game.createUnit(GameConstants.B52, Player.BLUE));
        ((CityImpl) game.getCityAt(pos1)).increaseSize();
        assertNotNull(game.getCityAt(pos1));
        game.performUnitActionAt(pos1);
        assertNotNull(game.getCityAt(pos1));
        assertThat(((CityImpl) game.getCityAt(pos1)).getSize(), is(1));
    }


    // BomberCanRemoveForrest
    // BomberCanRemoveCityAndForrest
    // Bombefly kan ikke blive ved med at bombe på samme tur
    // Kan modspilleren bruge din unitaction?

}