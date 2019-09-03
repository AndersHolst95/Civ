package hotciv.standard;
import hotciv.framework.*;

public class UnitImpl implements Unit{
    String type;
    Player owner;
    int attStrength;
    int defStrength;
    int moveCount;

    public UnitImpl(String type, Player owner, int attStrength, int defStrength, int moveCount){
        this.type = type;
        this.owner = owner;
        this.attStrength = attStrength;
        this.defStrength = defStrength;
        this.moveCount = moveCount;
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

    public void substractMoveCount(int move) {
        moveCount-= move;
    }
}
