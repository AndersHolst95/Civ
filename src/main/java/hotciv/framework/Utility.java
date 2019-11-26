package hotciv.framework;

import java.util.ArrayList;

public class Utility {

    /**
     * Returns the tiles surrounding a position along with the tile itself
     * @param pos The position in question
     * @return A list of the surrounding 9 tiles centered on pos
     */
    public static ArrayList<Position> nearestTileList(Position pos) {
        ArrayList<Position> posList = new ArrayList<>();

        // positions start at the right and runs clockwise
        posList.add(pos);
        if (pos.getRow() != 0) {
            posList.add(new Position(pos.getRow() - 1, pos.getColumn())); // north
            if (pos.getColumn() != GameConstants.WORLDSIZE-1)
                posList.add(new Position(pos.getRow() - 1, pos.getColumn() + 1)); // northeast
        }
        if (pos.getColumn() != GameConstants.WORLDSIZE-1) {
            posList.add(new Position(pos.getRow(), pos.getColumn() + 1)); // east
            if (pos.getRow() != GameConstants.WORLDSIZE-1) {
                posList.add(new Position(pos.getRow() + 1, pos.getColumn() + 1)); // southeast
            }
        }
        if (pos.getRow() != GameConstants.WORLDSIZE-1) {
            posList.add(new Position(pos.getRow() + 1, pos.getColumn())); // south
            if (pos.getColumn() != 0)
                posList.add(new Position(pos.getRow() + 1, pos.getColumn() - 1)); // southwest
        }
        if (pos.getColumn() != 0) {
            posList.add(new Position(pos.getRow(), pos.getColumn() - 1)); // west
            if (pos.getRow() != 0)
                posList.add(new Position(pos.getRow() - 1, pos.getColumn() - 1)); // northwest
        }
        return posList;
    }

    public static void notifyWorldChange(Position pos, ArrayList<GameObserver> observers){
        for(GameObserver observer : observers)
            observer.worldChangedAt(pos);
    }

    public static void notifyTurnChange(Player nextPlayer, ArrayList<GameObserver> observers){
        for(GameObserver observer : observers)
            observer.turnEnds(nextPlayer);
    }

    public static void notifyTileFocusChange(Position pos, ArrayList<GameObserver> observers){
        for(GameObserver observer : observers)
            observer.tileFocusChangedAt(pos);
    }

}
