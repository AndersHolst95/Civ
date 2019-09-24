package hotciv.framework.random;

public class DeterministicDieRoll implements RandomStrategy{
    int[] intList = new int[] {1,2,3,4,5,6};
    int index = 0;

    @Override
    public int getNext() {
        int n = intList[Math.floorMod(index,6)];
        index ++;
        return n;
    }
}
