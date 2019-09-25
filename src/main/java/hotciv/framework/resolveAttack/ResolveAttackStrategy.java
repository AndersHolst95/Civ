package hotciv.framework.resolveAttack;

import hotciv.framework.Position;

public interface ResolveAttackStrategy {

    boolean unitAttack(Position attacker, Position defender);
}

