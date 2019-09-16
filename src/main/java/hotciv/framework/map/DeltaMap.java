package hotciv.framework.map;

import hotciv.framework.GameConstants;
import hotciv.framework.map.Map;

public class DeltaMap implements Map {
    public String[][] getLayout() {
        String[][] map = new String[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        map[0] = new String[]{"o", "o", "o", "3", "4", "m", "6", "7", "8" ,"9", "10", "o", "o", "o", "o", "o"};
        map[1] = new String[]{"o", "o", "2", "h", "h", "5", "6", "7", "8" ,"f", "f", "f", "12", "13", "o", "o"};
        map[2] = new String[]{"o", "1", "2", "3", "4", "5", "m", "7", "8" ,"9", "o", "o", "o", "13", "14", "o"};
        map[3] = new String[]{"o", "1", "2", "m", "m", "m", "6", "7", "8" ,"9", "o", "o", "12", "13", "14", "15"};
        map[4] = new String[]{"o", "o", "o", "3", "4", "pc2", "6", "7", "h" ,"h", "10", "11", "12", "13", "o", "o"};
        map[5] = new String[]{"o", "1", "f", "3", "4", "5", "6", "7", "8" ,"9", "10", "h", "h", "13", "14", "o"};
        map[6] = new String[]{"o", "o", "o", "3", "4", "5", "o", "o", "o" ,"o", "o", "o", "o", "o", "o", "o"};
        map[7] = new String[]{"o", "1", "2", "3", "4", "5", "o", "7", "8" ,"9", "h", "11", "12", "m", "o", "o"};
        map[8] = new String[]{"o", "1", "2", "3", "4", "5", "o", "7", "8" ,"h", "10", "11", "pc1", "f", "o", "o"};
        map[9] = new String[]{"0", "f", "f", "f", "4", "5", "6", "7", "o" ,"9", "f", "f", "12", "13", "14", "15"};
        map[10] = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "o" ,"o", "o", "11", "12", "13", "14", "15"};
        map[11] = new String[]{"o", "1", "2", "m", "m", "m", "6", "7", "8" ,"9", "o", "o", "o", "o", "o", "o"};
        map[12] = new String[]{"o", "o", "2", "3", "4", "5", "6", "7", "f" ,"f", "10", "11", "12", "13", "o", "o"};
        map[13] = new String[]{"o", "o", "o", "o", "4", "5", "6", "7", "8" ,"9", "10", "11", "12", "o", "o", "o"};
        map[14] = new String[]{"o", "o", "2", "3", "4", "h", "h", "7", "8" ,"o", "o", "o", "o", "o", "o", "o"};
        map[15] = new String[]{"o", "o", "o", "o", "o", "5", "6", "7", "8" ,"9", "10", "11", "12", "13", "o", "o"};
        return map;
    }
}
