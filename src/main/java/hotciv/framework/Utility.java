package hotciv.framework;

public class Utility {

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



}
