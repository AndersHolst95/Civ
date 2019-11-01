package hotciv.standard.layout;
import hotciv.framework.GameConstants;
import thirdparty.ThirdPartyFractalGenerator;

public class FractalLayout implements LayoutStrategy {
    ThirdPartyFractalGenerator generator = new ThirdPartyFractalGenerator();
    public String[][] getLayout() {
        String[][] layout = new String[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        for (int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for (int j = 0; j < GameConstants.WORLDSIZE; j++) {
                Character c = generator.getLandscapeAt(i, j);
                if(c.equals('.'))
                    c = 'p';
                if(c.equals('M'))
                    c = 'm';
                layout[i][j] = c.toString();
            }
        }
        return layout;
    }
}
