package hotciv.framework.resolveAttack;

import hotciv.framework.Position;

public class AttackerWins implements ResolveAttackStrategy{
    @Override
    public boolean unitAttack(Position attacker, Position defender) {
        return true;
    }
}
