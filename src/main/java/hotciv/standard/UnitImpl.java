package hotciv.standard;
import hotciv.framework.*;

public class UnitImpl implements Unit{
    private String type;
    private Player owner;
    private int attStrength;
    private int defStrength;
    private int moveCount;
    private int cost;

    public UnitImpl(String type, Player owner){
        this.type = type;
        this.owner = owner;
        switch (type) {
            case GameConstants.ARCHER:
                attStrength = 2;
                defStrength = 3;
                moveCount = 1;
                cost = 10;
                break;

            case GameConstants.LEGION:
                attStrength = 4;
                defStrength = 2;
                moveCount = 1;
                cost = 15;
                break;

            case GameConstants.SETTLER:
                attStrength = 0;
                defStrength = 3;
                moveCount = 1;
                cost = 30;
                break;

        }
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getAttackingStrength() {
        return attStrength;
    }

    @Override
    public int getDefensiveStrength() {
        return defStrength;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public String getTypeString() {
        return type;
    }

    public void setMoveCount(int i) {
        moveCount = i;
        return;
    }

    public int getCost() {
        return cost;
    }

}
