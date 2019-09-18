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
        game = new GameImpl("beta");
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