package hotciv.framework.map;

import hotciv.framework.GameConstants;
import hotciv.framework.map.Map;

public class StandardMap implements Map {
    public String[][] getLayout() {
        String[][] map = new String[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        map[1][0] = "o";
        map[0][1] = "h";
        map[2][2] = "m";
        map[2][0] = "pa1";
        map[3][2] = "pl2";
        map[4][3] = "ps1";
        map[1][1] = "pc1";
        map[4][1] = "pc2";
        return map;
    }
}
