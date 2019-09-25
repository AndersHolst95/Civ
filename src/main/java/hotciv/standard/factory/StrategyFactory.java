package hotciv.standard.factory;

import hotciv.framework.age.AgeStrategy;
import hotciv.framework.layout.LayoutStrategy;
import hotciv.framework.random.DieRoll;
import hotciv.framework.random.RandomStrategy;
import hotciv.framework.resolveAttack.ResolveAttackStrategy;
import hotciv.framework.unitAction.UnitActionStrategy;
import hotciv.framework.victoryStrategy.*;

public interface StrategyFactory {
    RandomStrategy rand = new DieRoll();
    AgeStrategy getAgeStrategy();
    VictoryStrategy getVictoryStrategy();
    LayoutStrategy getLayoutStrategy();
    ResolveAttackStrategy getAttackStrategy();
    UnitActionStrategy getActionStrategy();
    static RandomStrategy getRandomStrategy(){return rand;}
}

