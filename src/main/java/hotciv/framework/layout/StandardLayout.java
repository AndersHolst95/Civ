package hotciv.framework.layout;

import hotciv.framework.*;
import hotciv.framework.layout.*;

public class StandardLayout implements Layout {
    public String[][] getLayout() {
        String[][] layout = new String[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        layout[1][0] = "o";
        layout[0][1] = "h";
        layout[2][2] = "m";
        layout[2][0] = "pa1";
        layout[3][2] = "pl2";
        layout[4][3] = "ps1";
        layout[1][1] = "pc1";
        layout[4][1] = "pc2";
        return layout;
    }
}
