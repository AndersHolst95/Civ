package hotciv.framework.age;

import hotciv.framework.*;
import hotciv.framework.age.AgeStrategy;

public class ConstantAging implements AgeStrategy {
    public int getNextYear(int age) {
        return age + 100;
    }
}
