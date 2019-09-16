package hotciv.framework.map;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

public interface Map {
    String[][] getLayout();

    static String tileInterpreter(char c) {
        switch (c) {
            case 'p': // plains
                return GameConstants.PLAINS;
            case 'o': // ocean
                return GameConstants.OCEANS;
            case 'm': // mountains
                return GameConstants.MOUNTAINS;
            case 'f': // forest
                return GameConstants.FOREST;
            case 'h': // hills
                return GameConstants.HILLS;
            default: // default is just plains
                return GameConstants.PLAINS;
        }
    }

    static Player playerInterpreter(char c) {
        switch (c) {
            case '1':
                return Player.RED;
            case '2':
                return Player.BLUE;
            default: // default is just red
                return Player.RED;
        }
    }

    static String unitInterpreter(char c) {
        switch (c) {
            case 'a': // archer
                return GameConstants.ARCHER;
            case 'l': // legion
                return GameConstants.LEGION;
            case 's': // settler
                return GameConstants.SETTLER;
            default: // default is an archer
                return GameConstants.ARCHER;
        }
    }

    /**
     * This method implements the basic map given at page 459.
     * @return the finished map as an array of tiles
     */

    static TileImpl[][] generateMap(String[][] layout){
        TileImpl[][] map = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        for(int i = 0; i < GameConstants.WORLDSIZE; i++){
            for(int j = 0; j< GameConstants.WORLDSIZE; j++){
                String string = layout[i][j];
                String type = GameConstants.PLAINS;
                CityImpl city = null;
                UnitImpl unit = null;
                if (string != null) {
                    switch (string.length()) {
                        case 1: // only a tile type is given
                            type = tileInterpreter(string.charAt(0));
                            break;
                        case 3: // a tile type and a city or unit is given
                            type = tileInterpreter(string.charAt(0));
                            if (string.charAt(1) == 'c') // if the second string is a city, create one with owner based on the third character
                                city = new CityImpl(1, 0, playerInterpreter(string.charAt(2)), GameConstants.ARCHER, null);
                            else // otherwise it is a unit
                                unit = new UnitImpl(unitInterpreter(string.charAt(1)), playerInterpreter(string.charAt(2)));
                            break;
                        case 5:
                            type = tileInterpreter(string.charAt(0));
                            city = new CityImpl(1, 0, playerInterpreter(string.charAt(2)), GameConstants.ARCHER, null);
                            unit = new UnitImpl(unitInterpreter(string.charAt(3)), playerInterpreter(string.charAt(4)));
                            break;
                        default:
                            break;
                    }
                }
                map[i][j] = new TileImpl(new Position(i,j), type, city, unit);
            }
        }
        return map;
    }
}

