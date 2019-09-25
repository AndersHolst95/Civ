package hotciv.framework.resolveAttack;

import hotciv.standard.UnitImpl;

public interface ResolveAttackStrategy {

    boolean unitAttack(UnitImpl attacker, UnitImpl defender);
}

