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
    TileImpl[][] map = generateMap();

    // ------------------------------------------ \\
    public Tile getTileAt(Position p) {
        return map[p.getRow()][p.getColumn()];
    }

    public Unit getUnitAt(Position p) {
        return map[p.getRow()][p.getColumn()].getUnit();
    }

    public City getCityAt(Position p) {
        return map[p.getRow()][p.getColumn()].getCity();
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

  /**
   * This method implements the basic map given at page 459.
   * @return the finished map as an array of tiles
   */
  private TileImpl[][] generateMap(){

      TileImpl[][] map = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

      // Inserting planes tiles everywhere
      for(int i = 0; i < GameConstants.WORLDSIZE; i++){
        for(int j = 0; j< GameConstants.WORLDSIZE; j++){
          map[i][j] = new TileImpl(new Position(i,j), "planes", null, null);
        }
      }
      // Inserting special terrain
      map[1][0].setType("ocean");
      map[0][1].setType("hills");
      map[2][2].setType("mountain");

      // Inserting special units
      map[2][0].setUnit(new UnitImpl("archer", Player.RED, 2, 3, 1));
      map[3][2].setUnit(new UnitImpl("legion", Player.BLUE, 4, 2, 1));
      map[4][3].setUnit(new UnitImpl("settler", Player.RED, 0, 3, 1));

      // Inserting starting cities
      map[1][1].setCity(new CityImpl(1, 0, Player.RED, null, null));
      map[4][1].setCity(new CityImpl(1, 0, Player.BLUE, null, null));

      return map;
    }
}
