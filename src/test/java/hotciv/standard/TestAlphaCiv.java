package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/**
 * Skeleton class for AlphaCiv test cases
 * <p>
 * Updated Oct 2015 for using Hamcrest matchers
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class TestAlphaCiv {
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
        game = new GameImpl();
    }

    @Test
    public void redGoesFirst() {
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void gameStartYearIs4000BC() {
        assertThat(game.getAge(), is(-4000));
    }

    @Test
    public void gameTimeIncrementsBy100Years() {
        int age = game.getAge();
        endRound();
        assertThat(game.getAge(), is(age + 100));
    }

    @Test
    public void redWinsInYear3000BC() {
        while (game.getAge() < -3000)
            game.endOfTurn();
        assertThat(game.getAge(), is(-3000));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void noWinnerBefore3000BC() {
        while (game.getAge() < -3000) {
            assertThat(game.getWinner(), is(nullValue()));
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(-3000));
        assertThat(game.getWinner(), is(not(nullValue())));
    }

    @Test
    public void blueAfterRed() {
        assertThat(game.getPlayerInTurn(), is(Player.RED));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void correctStartingUnits() {
        // check the units are created
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is(GameConstants.ARCHER));
        assertThat(game.getUnitAt(new Position(3, 2)).getTypeString(), is(GameConstants.LEGION));
        assertThat(game.getUnitAt(new Position(4, 3)).getTypeString(), is(GameConstants.SETTLER));
        // check ownership
        assertThat(game.getUnitAt(new Position(2, 0)).getOwner(), is(Player.RED));
        assertThat(game.getUnitAt(new Position(3, 2)).getOwner(), is(Player.BLUE));
        assertThat(game.getUnitAt(new Position(4, 3)).getOwner(), is(Player.RED));
    }

    @Test
    public void correctStartingCities() {
        assertThat(game.getCityAt(new Position(1, 1)).getOwner(), is(Player.RED));
        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void correctStartingTerrain() {
        assertThat(game.getTileAt(new Position(1, 0)).getTypeString(), is(GameConstants.OCEANS));
        assertThat(game.getTileAt(new Position(0, 1)).getTypeString(), is(GameConstants.HILLS));
        assertThat(game.getTileAt(new Position(2, 2)).getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void noCityGrowth() {
        City city11 = game.getCityAt(new Position(1, 1));
        assertThat(city11.getSize(), is(1));
        for (int i = 0; i < 10; i++) {
            endRound();
        }
        assertThat(city11.getSize(), is(1));
    }

    @Test
    public void limitedMovementCount() {
        Position from = new Position(2, 0);
        Position to = new Position(3, 0);
        Position toto = new Position(4, 0);
        assertThat((game.getUnitAt(from).getMoveCount()), is(1));
        assertTrue(game.moveUnit(from, to));
        assertFalse(game.moveUnit(to, toto)); // trying to move again
    }

    @Test
    public void illegalMovement() {
        Position from = new Position (2, 0); // starting position
        Position unit = new Position (2, 1); // friendly unit
        Position mountain = new Position (3, 0); // impassable mountain
        Position ocean = new Position (1, 0); // impassable ocean
        Position tooLong = new Position(GameConstants.WORLDSIZE-1, GameConstants.WORLDSIZE-1); // too far away

        game.setTypeAt(mountain, GameConstants.MOUNTAINS);
        game.setUnitAt(unit, new UnitImpl(GameConstants.ARCHER, Player.RED));
        assertFalse(game.moveUnit(new Position(5, 5), new Position(5, 4))); // trying to move nothing
        assertFalse(game.moveUnit(from, tooLong)); // trying to move too far
        assertFalse(game.moveUnit(from, ocean)); // trying to move onto an ocean
        assertFalse(game.moveUnit(from, mountain)); // trying to move onto a mountain
        assertFalse(game.moveUnit(from, unit)); // trying to move onto a friendly unit
    }

    @Test
    public void canMoveDiagonally() {
        Position from = new Position (2, 0);
        Position to = new Position (3, 1);
        assertTrue(game.moveUnit(from, to));
    }

    @Test
    public void blueCannotMoveRed(){
        game.endOfTurn();
        assertFalse(game.moveUnit(new Position(2,0), new Position(2,1))); // blue cannot move red
    }

    @Test
    public void redCannotMoveBlue(){
        assertFalse(game.moveUnit(new Position(3,2), new Position(4,2))); // red cannot move blue
    }

    @Test
    public void onlyTwoPlayersParticipating() {
        while (game.getWinner() == null) {
            assertTrue(game.getPlayerInTurn() == Player.BLUE || game.getPlayerInTurn() == Player.RED);
            game.endOfTurn();
        }
    }

    @Test
    public void onlyOneUnitCanOccupyATile() {
        Position pos = new Position(5, 5 );
        assertTrue(game.setUnitAt(pos, new UnitImpl(GameConstants.ARCHER, Player.BLUE))); // can place a unit on a free tile
        assertFalse(game.setUnitAt(pos, new UnitImpl(GameConstants.ARCHER, Player.RED))); // cannot place another red unit on the same tile
    }

    @Test
    public void redAttackerWins() {
        Position redPos = new Position(5, 5);
        Position bluePos = new Position(5, 6);
        game.setUnitAt(redPos, new UnitImpl(GameConstants.ARCHER, Player.RED));
        game.setUnitAt(bluePos, new UnitImpl(GameConstants.ARCHER, Player.BLUE));

        assertTrue(game.moveUnit(redPos, bluePos)); // red is moved
        assertThat(game.getUnitAt(bluePos).getOwner(), is(Player.RED)); // check that red won the fight
    }

    @Test
    public void blueAttackerWins() {
        Position redPos = new Position(5, 6);
        Position bluePos = new Position(5, 7);
        game.setUnitAt(redPos, new UnitImpl(GameConstants.ARCHER, Player.RED));
        game.setUnitAt(bluePos, new UnitImpl(GameConstants.ARCHER, Player.BLUE));

        game.endOfTurn(); // change turn so the blue unit can be moved
        assertTrue(game.moveUnit(bluePos, redPos)); // blue is moved
        assertThat(game.getUnitAt(redPos).getOwner(), is(Player.BLUE)); // check that blue won the fight
    }

    @Test
    public void unitsCannotBePlacedOnOcean() {
        assertFalse(game.setUnitAt(new Position(1, 0), new UnitImpl(GameConstants.ARCHER, Player.RED))); // ocean
    }

    @Test
    public void unitsCannotBePlacedOnMountain() {
        assertFalse(game.setUnitAt(new Position(2, 2), new UnitImpl(GameConstants.ARCHER, Player.RED))); // mountain
    }

    @Test
    public void citiesProduce6ProductionPerRound() {
        Position pos1 = new Position(1,1);
        int city1Prod = ((CityImpl) game.getCityAt(pos1)).getProductionValue();
        endRound();
        assertEquals(((CityImpl) game.getCityAt(pos1)).getProductionValue(), city1Prod + 6);
    }

    @Test
    public void cityCanProduceArcher() {
        Position cityPos = new Position(1, 1);
        endRound();
        endRound();
        assertThat(game.getUnitAt(cityPos).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void cityCanProduceLegion() {
        Position cityPos = new Position(1, 1);
        ((CityImpl)game.getCityAt(cityPos)).setProduction(GameConstants.LEGION);
        endRound();
        endRound();
        endRound();
        assertThat(game.getUnitAt(cityPos).getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void cityCanProduceSettler() {
        Position cityPos = new Position(1, 1);
        ((CityImpl)game.getCityAt(cityPos)).setProduction(GameConstants.SETTLER);
        for (int i = 0; i < 5; i++) {
            endRound();
        }
        assertThat(game.getUnitAt(cityPos).getTypeString(), is(GameConstants.SETTLER));
    }

    @Test
    public void cityPlacesProducedUnitCorrectly() {
        for (int i = 0; i < 6; i++)
            endRound();
        assertThat(game.getUnitAt(new Position(1, 1)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(0, 1)), is(notNullValue()));
        assertThat(game.getUnitAt(new Position(0, 2)), is(notNullValue()));
    }

    @Test
    public void refreshMovementAtEndOfRound() {
        assertTrue(game.moveUnit(new Position(2, 0), new Position(3, 0))); // move the unit
        endRound();
        assertTrue(game.moveUnit(new Position(3, 0), new Position(4, 0))); // move the unit
    }

    @Test
    public void canNotMoveOutsideOfTheMap() {
        assertFalse(game.moveUnit(new Position(2, 0), new Position(2, -1)));
    }
}
