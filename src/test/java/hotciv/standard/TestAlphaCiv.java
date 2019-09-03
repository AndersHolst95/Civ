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
    private Game game;

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl();
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void shouldBeRedAsStartingPlayer() {
        assertThat(game, is(notNullValue()));
        // TODO: reenable the assert below to get started...
        // assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    /**
     * REMOVE ME. Not a test of HotCiv, just an example of what
     * matchers the hamcrest library has...
     */
    @Test
    public void shouldDefinetelyBeRemoved() {
        // Matching null and not null values
        // 'is' require an exact match
        String s = null;
        assertThat(s, is(nullValue()));
        s = "Ok";
        assertThat(s, is(notNullValue()));
        assertThat(s, is("Ok"));

        // If you only validate substrings, use containsString
        assertThat("This is a dummy test", containsString("dummy"));

        // Match contents of Lists
        List<String> l = new ArrayList<String>();
        l.add("Bimse");
        l.add("Bumse");
        // Note - ordering is ignored when matching using hasItems
        assertThat(l, hasItems(new String[]{"Bumse", "Bimse"}));

        // Matchers may be combined, like is-not
        assertThat(l.get(0), is(not("Bumse")));
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
        game.endOfTurn();
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
        assertThat(game.getUnitAt(new Position(2, 0)).getTypeString(), is("archer"));
        assertThat(game.getUnitAt(new Position(3, 2)).getTypeString(), is("legion"));
        assertThat(game.getUnitAt(new Position(4, 3)).getTypeString(), is("settler"));
    }

    @Test
    public void correctStartingCities() {
        assertThat(game.getCityAt(new Position(1, 1)).getOwner(), is(Player.RED));
        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void correctStartingTerrain() {
        assertThat(game.getTileAt(new Position(1, 0)).getTypeString(), is("ocean"));
        assertThat(game.getTileAt(new Position(0, 1)).getTypeString(), is("hills"));
        assertThat(game.getTileAt(new Position(2, 2)).getTypeString(), is("mountain"));
    }

    @Test
    public void noCityGrowth() {
        while (game.getWinner() == null) {
            game.endOfTurn();
            for(int i = 0; i < GameConstants.WORLDSIZE; i++){
                for(int j = 0; j< GameConstants.WORLDSIZE; j++){
                    City city = game.getCityAt(new Position(i, j));
                    if (city != null)
                        assertThat(city.getSize(), is(1));
                }
            }
        }
        assertThat(game.getAge(), is(-3000));
        assertThat(game.getWinner(), is(not(nullValue())));
    }

    @Test
    public void limitedMovementCount(){
        Position from = new Position(2,0);
        Position to = new Position(2,1);
        Position toto = new Position(2,2);
        int moveCount = game.getUnitAt(from).getMoveCount();
        game.moveUnit(from, to);
        assertThat(moveCount - 1, is(game.getUnitAt(to).getMoveCount()));
        game.moveUnit(to, toto);
        assertThat(game.getUnitAt(toto), is(nullValue())); // It has not moved away from to
        assertThat(0, is(game.getUnitAt(to).getMoveCount()));

        // Missing: Checking whether the move is illegal!

    }
}
