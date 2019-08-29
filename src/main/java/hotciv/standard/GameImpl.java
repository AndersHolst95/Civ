package hotciv.standard;

import hotciv.framework.*;

/**
 * Skeleton implementation of HotCiv.
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

public class GameImpl implements Game {
    // ---------- Initialize the world ---------- \\
    int worldAge = -4000;
    Player currentPlayer = Player.RED;


    // ------------------------------------------ \\
    public Tile getTileAt(Position p) {
        return new TileImpl();
    }

    public Unit getUnitAt(Position p) {
        return null;
    }

    public City getCityAt(Position p) {
        return new CityImpl();
    }

    public Player getPlayerInTurn() {
        return currentPlayer;
    }

    public Player getWinner() {
        return worldAge == -3000 ? Player.RED : null;
    }

    public int getAge() {
        return worldAge;
    }

    public boolean moveUnit(Position from, Position to) {
        return false;
    }

    public void endOfTurn() {
        worldAge += 100;
        if (currentPlayer == Player.RED)
            currentPlayer = Player.BLUE;
        else if (currentPlayer == Player.BLUE)
            currentPlayer = Player.RED;
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
    }

    public void performUnitActionAt(Position p) {
    }
}
