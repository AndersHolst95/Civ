package hotciv.standard.age;

public class ConstantAging implements AgeStrategy {
    public int getNextYear(int age) {
        return age + 100;
    }
}
