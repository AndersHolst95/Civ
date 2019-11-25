package hotciv.standard;
import hotciv.framework.*;

import java.util.UUID;

public class UnitImpl implements Unit {
    private String type;
    private Player owner;
    private int attStrength;
    private int defStrength;
    private int movement; // number of movement points for the unit
    private int moveCount; // current number of movement points for the unit
    private int cost;
    private boolean isFortified = false; // is the unit fortified
    private boolean usedAction = false;
    private String id = UUID.randomUUID().toString();

    public UnitImpl(String type, Player owner){
        this.type = type;
        this.owner = owner;
        this.attStrength = GameConstants.UNITS.toClass(type).getAttStrength();
        this.defStrength = GameConstants.UNITS.toClass(type).getDefStrength();
        this.movement = GameConstants.UNITS.toClass(type).getMovement();
        this.cost = GameConstants.UNITS.toClass(type).getCost();
        moveCount = movement;
    }
    public String getId() {
        return id;
    }

    public Player getOwner() {
        return owner;
    }

    public int getAttackingStrength() {
        return attStrength;
    }

    public int getDefensiveStrength() {
        return defStrength;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public String getTypeString() {
        return type;
    }

    public void refreshMoveCount() {
        moveCount = movement;
    }

    public void setMoveCount(int i) {
        moveCount = i;
    }

    public boolean isFortified() {
        return isFortified;
    }

    public void toggleFortify() {
        if (!isFortified) {
            isFortified = true;
            defStrength = 2*defStrength;
            movement = 0;
            moveCount = 0;
        }
        else {
            isFortified = false;
            defStrength = defStrength/2;
            movement = GameConstants.UNITS.ARCHER.movement;
        }
    }

    public int getCost() {
        return cost;
    }

    public void setUsedAction(boolean usedAction) {
        this.usedAction = usedAction;
    }

    public boolean getUsedAction() {
        return usedAction;
    }
}