package hotciv.standard.factory;

import hotciv.framework.age.AgeStrategy;
import hotciv.framework.layout.LayoutStrategy;
import hotciv.framework.resolveAttack.ResolveAttackStrategy;
import hotciv.framework.unitAction.UnitActionStrategy;
import hotciv.framework.victoryStrategy.*;
import hotciv.framework.workforce.WorkforceStrategy;

public interface StrategyFactory {
    AgeStrategy createAgeStrategy();
    VictoryStrategy createVictoryStrategy();
    LayoutStrategy createLayoutStrategy();
    ResolveAttackStrategy createAttackStrategy();
    UnitActionStrategy createActionStrategy();
    WorkforceStrategy createWorkforceStrategy();
}

