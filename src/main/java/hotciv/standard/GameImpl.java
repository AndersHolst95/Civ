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
        UnitImpl unit = map[from.getRow()][from.getColumn()].getUnit();

        // Check if unit exists
        if (unit == null)
            return false;

        // Check if unit has enough movement points left
        if (unit.getMoveCount() > 0)
            unit.setMoveCount(unit.getMoveCount() - 1);
        else
            return false;

        // Check if terrain is impassable
        String type = map[to.getRow()][to.getColumn()].getTypeString();
        if (type.equals(GameConstants.MOUNTAINS) || type.equals(GameConstants.OCEANS))
            return false;

        // Check friendly unit collision
        Unit toUnit = map[to.getRow()][to.getColumn()].getUnit();
        if (toUnit != null && toUnit.getOwner() == currentPlayer)
            return false;

        // Check if destination is a single tile away
        boolean valid = false;
        if (Math.abs(from.getColumn() - to.getColumn()) == 1) {
            if (Math.abs(from.getRow() - to.getRow()) <= 1)
                valid = true;
        }
        if (Math.abs(from.getRow() - to.getRow()) == 1) {
            if (Math.abs(from.getColumn() - to.getColumn()) == 0)
                valid = true;
        }
        if (!valid)
                return false;
        // ANOTHER VERSION OF CALCULATING THE LENGTH OF MOVEMENT //
        // The unit has moved more than one tile
        //double d = Math.sqrt(java.lang.Double.sum(Math.pow(to.getRow()-from.getRow(), 2) , Math.pow(to.getColumn()-from.getColumn(), 2)));
        //if (d > 2){ return false;}

        map[to.getRow()][to.getColumn()].setUnit(unit); // replaces unit on to
        map[from.getRow()][from.getColumn()].setUnit(null); // removes unit on from
        return true;
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

    public void setTypeAt(Position pos, String type) {
        map[pos.getRow()][pos.getColumn()].setType(type);
    }

    public void setUnitAt(Position pos, UnitImpl unit) {
        map[pos.getRow()][pos.getColumn()].setUnit(unit);
    }

    public void setCityAt(Position pos, CityImpl city) {
        map[pos.getRow()][pos.getColumn()].setCity(city);
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
          map[i][j] = new TileImpl(new Position(i,j), GameConstants.PLAINS, null, null);
        }
      }
      // Inserting special terrain
      map[1][0].setType(GameConstants.OCEANS);
      map[0][1].setType(GameConstants.HILLS);
      map[2][2].setType(GameConstants.MOUNTAINS);

      // Inserting special units
      map[2][0].setUnit(new UnitImpl(GameConstants.ARCHER, Player.RED, 2, 3, 1));
      map[3][2].setUnit(new UnitImpl(GameConstants.LEGION, Player.BLUE, 4, 2, 1));
      map[4][3].setUnit(new UnitImpl(GameConstants.SETTLER, Player.RED, 0, 3, 1));

      // Inserting starting cities
      map[1][1].setCity(new CityImpl(1, 0, Player.RED, null, null));
      map[4][1].setCity(new CityImpl(1, 0, Player.BLUE, null, null));

      return map;
    }
}
