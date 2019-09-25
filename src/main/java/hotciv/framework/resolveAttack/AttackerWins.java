package hotciv.framework.resolveAttack;

import hotciv.standard.UnitImpl;

public class AttackerWins implements ResolveAttackStrategy{
    @Override
    public boolean unitAttack(UnitImpl attacker, UnitImpl defender) {
        return true;
    }
}
