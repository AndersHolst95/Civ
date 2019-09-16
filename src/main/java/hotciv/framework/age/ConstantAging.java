package hotciv.framework.age;

import hotciv.framework.GameConstants;
import hotciv.framework.age.Age;

public class ConstantAging implements Age {
    private int age = GameConstants.STARTYEAR;
    public void changeWorldAge() {
        age += 100;
    }
    public int getAge() {
        return age;
    }
}
