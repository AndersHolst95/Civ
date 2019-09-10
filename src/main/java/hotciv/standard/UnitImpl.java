package hotciv.standard;
import hotciv.framework.*;

public class UnitImpl implements Unit{
    private String type;
    private Player owner;
    private int attStrength;
    private int defStrength;
    private final int movement; // number of movement points for the unit
    private int moveCount; // current number of movement points for the unit
    private int cost;

    public UnitImpl(String type, Player owner){
        this.type = type;
        this.owner = owner;
        switch (type) {
            case GameConstants.ARCHER:
                attStrength = 2;
                defStrength = 3;
                movement = 1;
                cost = 10;
                break;

            case GameConstants.LEGION:
                attStrength = 4;
                defStrength = 2;
                movement = 1;
                cost = 15;
                break;

            case GameConstants.SETTLER:
                attStrength = 0;
                defStrength = 3;
                movement = 1;
                cost = 30;
                break;

            default: // default unit is an archer - should probably throw an exception instead
                attStrength = 2;
                defStrength = 3;
                movement = 1;
                cost = 10;
        }
        moveCount = movement;
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

    public void refreshMoveCount() {
        moveCount = movement;
    }

    public void setMoveCount(int i) {
        moveCount = i;
        return;
    }

    public int getCost() {
        return cost;
    }

}
