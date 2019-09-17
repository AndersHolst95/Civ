package hotciv.framework.age;
import hotciv.framework.World;
public class GradualAging implements AgeStrategy {
    public int getNextYear(int age) {
        if(age < -100){age += 100;} // While the world is younger than 100BC, the world increments by 100 years
        else if(age == -100){age = -1;} // Around Christ the world goes -50, -1, 1 50
        else if(age == -1){age = 1;} // Around Christ
        else if(age == 1){age = 50;} // Around Christ
        else if(age >= 50 && age < 1750){age += 50;} //Between 50AD and 1750AD increment by 50
        else if(age >= 1750 && age < 1900){age += 25;} //Between 1750 and 1900 increment by 25
        else if(age >= 1900 && age < 1970){age += 5;} //Between 1900 and 1970 increment by 5
        else if(age >= 1970){age += 1;} //After 1970 increment by 1
        return age;
    }
}
