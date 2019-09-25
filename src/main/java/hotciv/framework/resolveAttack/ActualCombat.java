package hotciv.framework.resolveAttack;

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
    public boolean unitAttack(UnitImpl attacker, UnitImpl defender) {

        // Husk at ændre antal vundre kampe for vinderen


        return true;
    }
}
