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
public class TestGammaCiv {
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
        game = new GameImpl("gamma");
    }

    @Test
    public void gammaSettlerAction(){
        Position pos = new Position(4, 3); // a Settler is located at (4, 3)
        game.doUnitAction(pos);
        assertNull(game.getUnitAt(pos)); // check that the unit is gone
        assertNotNull(game.getCityAt(pos)); // check that a new city is created
        assertThat(game.getCityAt(pos).getSize(), is(1)); // verify the size is 1
    }

    @Test
    public void gammaArcherAction() {
        Position pos = new Position(2, 0); // an Archer is located at (2, 0)
        game.doUnitAction(pos);

        // check that the defensive value is doubled and that the unit cannot move
        assertThat(game.getUnitAt(pos).getDefensiveStrength(), is(2*GameConstants.UNITS.ARCHER.defStrength));
        assertFalse(game.moveUnit(pos, new Position(3, 0)));
        endRound();
        game.doUnitAction(pos);
        endRound();

        // check that its defensive value is normal and that it can move again
        assertThat(game.getUnitAt(pos).getDefensiveStrength(), is(GameConstants.UNITS.ARCHER.defStrength));
        assertTrue(game.moveUnit(pos, new Position(3, 0)));
    }
}
