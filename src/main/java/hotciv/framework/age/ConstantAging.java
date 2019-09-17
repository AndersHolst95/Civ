package hotciv.framework.age;

import hotciv.framework.GameConstants;
import hotciv.framework.World;

public class ConstantAging implements AgeStrategy {
    public int getNextYear(int age) {
        return age + 100;
    }
}
