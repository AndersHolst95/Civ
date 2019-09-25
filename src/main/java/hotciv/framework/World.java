package hotciv.framework;

import hotciv.framework.layout.*;
import hotciv.framework.*;
import hotciv.framework.resolveAttack.ResolveAttackStrategy;
import hotciv.standard.*;

public class World {
    private static TileImpl[][] map;

    public static void setMap(String[][] layout) {
        map = LayoutStrategy.generateMap(layout);
    }

    public static TileImpl[][] getMap() {
        return map;
    }

    public static Tile getTileAt(Position p) {
        return map[p.getRow()][p.getColumn()];
    }

    public static Unit getUnitAt(Position p) {
        return map[p.getRow()][p.getColumn()].getUnit();
    }

    public static City getCityAt(Position p) {
        return map[p.getRow()][p.getColumn()].getCity();
    }

    public static boolean moveUnit(Position from, Position to, ResolveAttackStrategy attackStrategy) {
        UnitImpl unit = map[from.getRow()][from.getColumn()].getUnit();

        // Check if unit exists
        if (unit == null)
            return false;

        // Checking if the unit has the right owner
        if(unit.getOwner() != GameVariables.currentPlayer){ return false;}

        // Check if unit has enough movement points left
        if (unit.getMoveCount() > 0)
            unit.setMoveCount(unit.getMoveCount() - 1);
        else
            return false;

        // Check if the "to" position is a valid position
        if (!validUnitPosition(to))
            return false;

        // Check unit collision
        UnitImpl toUnit = map[to.getRow()][to.getColumn()].getUnit();
        if (toUnit != null){
            // Friendly unit
             if(toUnit.getOwner() == GameVariables.currentPlayer)
                return false;

             // Enemy unit, resolve combat
             else{
                 boolean attackerWins = unitAttack(from, to, attackStrategy);
                  if (! attackerWins)
                      return false;
             }
        }

        // Check if destination is a single tile away
        // Note that this implies the difference in columns or rows is greater than 1
        if ((Math.abs(from.getColumn() - to.getColumn()) > 1) ||
                Math.abs(from.getRow() - to.getRow()) > 1)
            return false;

        map[to.getRow()][to.getColumn()].setUnit(unit); // replaces unit on to
        map[from.getRow()][from.getColumn()].setUnit(null); // removes unit on from
        return true;
    }

    private static boolean unitAttack(Position attacker, Position defender, ResolveAttackStrategy attackStrategy){
        if (attackStrategy.unitAttack(attacker, defender)){
            GameVariables.incrementVictory(getUnitAt(attacker).getOwner());
            return true;
        }
        return false;
    }

    /**
     * Returns the nearest valid tile for a unit
     * @param pos the position in question
     * @return the nearest free tile
     */
    public static Position getNearestAvailableTile(Position pos) {
        Position[] posList = nearestTileList(pos);
        for (int i = 0; i < 9; i++) {
            if (validUnitPosition(posList[i]) && (getUnitAt(posList[i]) == null))
                return posList[i];
        }
        return null;
    }

    /**
     * Returns the tiles surrounding a position along with the tile itself
     * @param pos The position in question
     * @return A list of the surrounding 9 tiles centered on pos
     */
    public static Position[] nearestTileList(Position pos) {
        Position[] posList = new Position[9];
        // positions start at the right and runs clockwise
        posList[0] = pos;
        posList[1] = new Position(pos.getRow() - 1, pos.getColumn()); // north
        posList[2] = new Position(pos.getRow() - 1, pos.getColumn() + 1); // northeast
        posList[3] = new Position(pos.getRow(), pos.getColumn() + 1); // east
        posList[4] = new Position(pos.getRow() + 1, pos.getColumn() + 1); // southeast
        posList[5] = new Position(pos.getRow() + 1, pos.getColumn()); // south
        posList[6] = new Position(pos.getRow() + 1, pos.getColumn() - 1); // southwest
        posList[7] = new Position(pos.getRow(), pos.getColumn() - 1); // west
        posList[8] = new Position(pos.getRow() - 1, pos.getColumn() - 1); // northwest
        return posList;
    }

    /**
     * Checks if the given position is within the world border, and if a city or unit can be placed on it
     * @param pos the parameter to be checked
     * @return true if valid
     */
    private static Boolean validUnitPosition(Position pos) {
        // Check for null-position
        if(pos == null)
            return false;

        // check for out-of-bounds
        if (pos.getColumn() < 0 || GameConstants.WORLDSIZE < pos.getColumn())
            return false;
        if (pos.getRow() < 0 || GameConstants.WORLDSIZE < pos.getRow())
            return false;

        // check for mountains and ocean
        if (getTileAt(pos).getTypeString().equals(GameConstants.MOUNTAINS) || getTileAt(pos).getTypeString().equals(GameConstants.OCEANS))
            return false;

        return true;
    }

    public static void setTypeAt(Position pos, String type) {
        map[pos.getRow()][pos.getColumn()].setType(type);
    }

    public static boolean setUnitAt(Position pos, UnitImpl unit) {
        if (!validUnitPosition(pos))
            return false;
        // check for other units
        if (getUnitAt(pos) != null)
            return false;

        map[pos.getRow()][pos.getColumn()].setUnit(unit);
        return true;
    }

    public static void setCityAt(Position pos, CityImpl city) {
        map[pos.getRow()][pos.getColumn()].setCity(city);
        setTypeAt(pos, GameConstants.CITY);
    }

    /**
     * Removes the unit located at positon pos
     * @param pos The position
     */
    public static void removeUnit(Position pos) {
        map[pos.getRow()][pos.getColumn()].setUnit(null);
    }
}
