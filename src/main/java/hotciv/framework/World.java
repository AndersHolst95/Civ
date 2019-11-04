package hotciv.framework;

import hotciv.standard.layout.*;
import hotciv.standard.resolveAttack.ResolveAttackStrategy;
import hotciv.standard.*;
import hotciv.standard.unitMovementDistinction.UnitMovementDistinctionStrategy;

import java.util.ArrayList;

public class World {
    private static TileImpl[][] map;

    public static void setMap(String[][] layout) {
        map = LayoutStrategy.generateMap(layout);
    }

    public static TileImpl[][] getMap() {
        return map;
    }

    public static Tile getTileAt(Position p) {
        if (p.getRow() < 0 || GameConstants.WORLDSIZE <= p.getRow()
                || p.getColumn() < 0 || GameConstants.WORLDSIZE <= p.getColumn())
            return null;
        return map[p.getRow()][p.getColumn()];
    }

    public static Unit getUnitAt(Position p) {
        if (p.getRow() < 0 || GameConstants.WORLDSIZE <= p.getRow()
                || p.getColumn() < 0 || GameConstants.WORLDSIZE <= p.getColumn())
            return null;
        return map[p.getRow()][p.getColumn()].getUnit();
    }

    public static City getCityAt(Position p) {
        if (p.getRow() < 0 || GameConstants.WORLDSIZE <= p.getRow()
                || p.getColumn() < 0 || GameConstants.WORLDSIZE <= p.getColumn())
            return null;
        return map[p.getRow()][p.getColumn()].getCity();
    }

    public static boolean moveUnit(Position from, Position to, ResolveAttackStrategy attackStrategy, UnitMovementDistinctionStrategy moveStrategy) {
        UnitImpl unit = map[from.getRow()][from.getColumn()].getUnit();

        // Check if unit exists
        if (unit == null)
            return false;

        // Checking if the unit has the right owner
        if(unit.getOwner() != GameVariables.currentPlayer){ return false;}

        // Check if unit has enough movement points left
        if (!(unit.getMoveCount() > 0))
            return false;

        // Check if the "to" position is a valid position
        if (!validUnitPosition(to, unit.getTypeString(), moveStrategy))
            return false;

        // Check if destination is a single tile away
        // Note that this implies the difference in columns or rows is greater than 1
        if ((Math.abs(from.getColumn() - to.getColumn()) > 1) ||
                Math.abs(from.getRow() - to.getRow()) > 1)
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
                  if (! attackerWins) {
                      removeUnit(from); // the attacker dies
                      return false;
                  }
             }
        }

        // Conquer any city entered
        if (getCityAt(to) != null)
            ((CityImpl) getCityAt(to)).setOwner(GameVariables.currentPlayer);

        unit.setMoveCount(unit.getMoveCount() - 1);
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
    public static Position getNearestAvailableTile(Position pos, String type, UnitMovementDistinctionStrategy moveStrategy) {
        ArrayList<Position> posList = Utility.nearestTileList(pos);
        for (Position availablePos : posList) {
            if (validUnitPosition(availablePos, type, moveStrategy) && (getUnitAt(availablePos) == null))
                return availablePos;
        }
        return null;
    }

    /**
     * Checks if the given position is within the world border, and if a city or unit can be placed on it
     * @param pos the parameter to be checked
     * @return true if valid
     */
    private static boolean validUnitPosition(Position pos, String type, UnitMovementDistinctionStrategy strategy) {
        return strategy.validUnitPosition(pos,type);
    }

    public static void setTypeAt(Position pos, String type) {
        map[pos.getRow()][pos.getColumn()].setType(type);
    }

    public static boolean setUnitAt(Position pos, UnitImpl unit, UnitMovementDistinctionStrategy moveStrategy) {
        if (!validUnitPosition(pos, unit.getTypeString(), moveStrategy))
            return false;
        // check for other units
        if (getUnitAt(pos) != null)
            return false;

        map[pos.getRow()][pos.getColumn()].setUnit(unit);
        return true;
    }

    public static void setCityAt(CityImpl city){
        Position pos = city.getLocation();
        map[pos.getRow()][pos.getColumn()].setCity(city);
    }

    /**
     * Removes the unit located at positon pos
     * @param pos The position
     */
    public static void removeUnit(Position pos) {
        map[pos.getRow()][pos.getColumn()].setUnit(null);
    }

    public static void removeCity(Position pos) { map[pos.getRow()][pos.getColumn()].setCity(null); }
}