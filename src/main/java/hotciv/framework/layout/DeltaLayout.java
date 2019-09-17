package hotciv.framework.layout;

import hotciv.framework.*;

public class DeltaLayout implements LayoutStrategy {
    public String[][] getLayout() {
        String[][] layout = new String[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        layout[0] = new String[]{"o", "o", "o", "3", "4", "m", "6", "7", "8" ,"9", "10", "o", "o", "o", "o", "o"};
        layout[1] = new String[]{"o", "o", "2", "h", "h", "5", "6", "7", "8" ,"f", "f", "f", "12", "13", "o", "o"};
        layout[2] = new String[]{"o", "1", "2", "3", "4", "5", "m", "7", "8" ,"9", "o", "o", "o", "13", "14", "o"};
        layout[3] = new String[]{"o", "1", "2", "m", "m", "m", "6", "7", "8" ,"9", "o", "o", "12", "13", "14", "15"};
        layout[4] = new String[]{"o", "o", "o", "3", "4", "pc2", "6", "7", "h" ,"h", "10", "11", "12", "13", "o", "o"};
        layout[5] = new String[]{"o", "1", "f", "3", "4", "5", "6", "7", "8" ,"9", "10", "h", "h", "13", "14", "o"};
        layout[6] = new String[]{"o", "o", "o", "3", "4", "5", "o", "o", "o" ,"o", "o", "o", "o", "o", "o", "o"};
        layout[7] = new String[]{"o", "1", "2", "3", "4", "5", "o", "7", "8" ,"9", "h", "11", "12", "m", "o", "o"};
        layout[8] = new String[]{"o", "1", "2", "3", "4", "5", "o", "7", "8" ,"h", "10", "11", "pc1", "f", "o", "o"};
        layout[9] = new String[]{"0", "f", "f", "f", "4", "5", "6", "7", "o" ,"9", "f", "f", "12", "13", "14", "15"};
        layout[10] = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "o" ,"o", "o", "11", "12", "13", "14", "15"};
        layout[11] = new String[]{"o", "1", "2", "m", "m", "m", "6", "7", "8" ,"9", "o", "o", "o", "o", "o", "o"};
        layout[12] = new String[]{"o", "o", "2", "3", "4", "5", "6", "7", "f" ,"f", "10", "11", "12", "13", "o", "o"};
        layout[13] = new String[]{"o", "o", "o", "o", "4", "5", "6", "7", "8" ,"9", "10", "11", "12", "o", "o", "o"};
        layout[14] = new String[]{"o", "o", "2", "3", "4", "h", "h", "7", "8" ,"o", "o", "o", "o", "o", "o", "o"};
        layout[15] = new String[]{"o", "o", "o", "o", "o", "5", "6", "7", "8" ,"9", "10", "11", "12", "13", "o", "o"};
        return layout;
    }
}
