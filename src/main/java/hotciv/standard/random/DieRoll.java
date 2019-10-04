package hotciv.standard.random;

import java.util.Random;

public class DieRoll implements RandomStrategy{
    Random rand = new Random();

    @Override
    public int getNext() {
        return  1 + rand.nextInt(6); // Returns a number between 1 and 6 (inclusive)
    }
}
