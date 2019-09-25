package hotciv.framework.resolveAttack;

import hotciv.framework.*;
import hotciv.framework.Position;
import hotciv.framework.World;
import hotciv.framework.random.RandomStrategy;
import hotciv.framework.random.DieRoll;
import hotciv.standard.UnitImpl;

public class ActualCombat implements  ResolveAttackStrategy{
    RandomStrategy rand;

    public ActualCombat(RandomStrategy rand){
       this.rand = rand;
    }
    public ActualCombat(){
        this.rand = new DieRoll();
    }

    @Override
    public boolean unitAttack(Position attPos, Position defPos) {
        // Husk at ændre antal vundre kampe for vinderen
        UnitImpl attacker = (UnitImpl) World.getUnitAt(attPos);
        UnitImpl defender = (UnitImpl) World.getUnitAt(defPos);

        // TERRAIN BONUS
        int attBonus;
        if (World.getCityAt(attPos) == null) // if there is not a city at the attacker..
            attBonus = GameConstants.TILE.toClass(World.getTileAt(attPos).getTypeString()).getCombatBonus(); // use the terrain bonus
        else // use the city bonus
            attBonus = GameConstants.TILE.CITY_COMBAT_BONUS;

        int defBonus;
        if (World.getCityAt(defPos) == null) // if there is not a city at the defender..
            defBonus = GameConstants.TILE.toClass(World.getTileAt(defPos).getTypeString()).getCombatBonus(); // use the terrain bonus
        else // use the city bonus
            defBonus = GameConstants.TILE.CITY_COMBAT_BONUS;

        // FRIENDLY SUPPORT
        int attSupport = checkFriendlySupport(attPos);
        int defSupport = checkFriendlySupport(defPos);

        // COMBINED COMBAT STATS
        int combinedAtt = (attacker.getAttackingStrength() + attSupport)*rand.getNext()*attBonus;
        int combinedDef = (defender.getDefensiveStrength() + defSupport)*rand.getNext()*defBonus;
        return combinedAtt > combinedDef;
    }

    private int checkFriendlySupport(Position pos) {
        int n = 0; // the current bonus from supporting units
        Player owner = World.getUnitAt(pos).getOwner(); // the owner of the unit

        Position[] posList = new Position[8];
        // positions start at the right and runs clockwise
        posList[0] = new Position(pos.getRow() - 1, pos.getColumn()); // north
        posList[1] = new Position(pos.getRow() - 1, pos.getColumn() + 1); // northeast
        posList[2] = new Position(pos.getRow(), pos.getColumn() + 1); // east
        posList[3] = new Position(pos.getRow() + 1, pos.getColumn() + 1); // southeast
        posList[4] = new Position(pos.getRow() + 1, pos.getColumn()); // south
        posList[5] = new Position(pos.getRow() + 1, pos.getColumn() - 1); // southwest
        posList[6] = new Position(pos.getRow(), pos.getColumn() - 1); // west
        posList[7] = new Position(pos.getRow() - 1, pos.getColumn() - 1); // northwest

        for (int i = 0; i < 8; i++) { // iterating through each nearby tile
            Unit unit = World.getUnitAt(posList[i]); // retrieving the unit
            if (unit != null && unit.getOwner().equals(owner)) // if a friendly unit exists on the tile
                n++; // increase the number of supporting units
        }
        return n;
    }
}
