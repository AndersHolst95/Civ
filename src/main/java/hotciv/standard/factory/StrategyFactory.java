package hotciv.standard.factory;

import hotciv.standard.availableUnit.AvailableUnitStrategy;
import hotciv.standard.age.AgeStrategy;
import hotciv.standard.layout.LayoutStrategy;
import hotciv.standard.resolveAttack.ResolveAttackStrategy;
import hotciv.standard.unitAction.UnitActionStrategy;
import hotciv.standard.unitMovementDistinction.UnitMovementDistinctionStrategy;
import hotciv.standard.victoryStrategy.*;
import hotciv.standard.workforce.WorkforceStrategy;

public interface StrategyFactory {
    AgeStrategy createAgeStrategy();
    VictoryStrategy createVictoryStrategy();
    LayoutStrategy createLayoutStrategy();
    ResolveAttackStrategy createAttackStrategy();
    UnitActionStrategy createActionStrategy();
    WorkforceStrategy createWorkforceStrategy();
    AvailableUnitStrategy createAvailableUnitStrategy();
    UnitMovementDistinctionStrategy createUnitMovementDistinctionStrategy();
}

