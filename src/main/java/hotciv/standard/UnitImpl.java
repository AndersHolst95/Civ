package hotciv.standard;
import hotciv.framework.*;

public class UnitImpl implements Unit{
    private String type;
    private Player owner;
    private int attStrength;
    private int defStrength;
    private int moveCount;

    public UnitImpl(String type, Player owner, int attStrength, int defStrength, int moveCount){
        this.type = type;
        this.owner = owner;
        this.attStrength = attStrength;
        this.defStrength = defStrength;
        this.moveCount = moveCount;
    }
    public UnitImpl(String type, Player owner){
        this.type = type;
        this.owner = owner;
        switch (type) {
            case GameConstants.ARCHER:
                this.attStrength = 2;
                this.defStrength = 3;
                this.moveCount = 1;
                break;

            case GameConstants.LEGION:
                this.attStrength = 4;
                this.defStrength = 2;
                this.moveCount = 1;
                break;

            case GameConstants.SETTLER:
                this.attStrength = 0;
                this.defStrength = 3;
                this.moveCount = 1;
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
}
