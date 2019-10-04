package hotciv.standard.factory;

import hotciv.framework.age.AgeStrategy;
import hotciv.framework.age.ConstantAging;
import hotciv.framework.age.GradualAging;
import hotciv.framework.layout.DeltaLayout;
import hotciv.framework.layout.LayoutStrategy;
import hotciv.framework.resolveAttack.ActualCombat;
import hotciv.framework.resolveAttack.AttackerWins;
import hotciv.framework.resolveAttack.ResolveAttackStrategy;
import hotciv.framework.unitAction.GammaAction;
import hotciv.framework.unitAction.NoAction;
import hotciv.framework.unitAction.UnitActionStrategy;
import hotciv.framework.victoryStrategy.ThreeCombatVictories;
import hotciv.framework.victoryStrategy.TimeVictory;
import hotciv.framework.victoryStrategy.VictoryStrategy;
import hotciv.framework.workforce.NoWorkableTiles;
import hotciv.framework.workforce.WorkableTiles;
import hotciv.framework.workforce.WorkforceStrategy;

public class SemiFactory implements StrategyFactory {
    public AgeStrategy createAgeStrategy() { return new GradualAging(); }
    public VictoryStrategy createVictoryStrategy() { return new ThreeCombatVictories();}
    public LayoutStrategy createLayoutStrategy() { return new DeltaLayout(); }
    public ResolveAttackStrategy createAttackStrategy() { return new ActualCombat(); }
    public UnitActionStrategy createActionStrategy() { return new GammaAction(); }
    public WorkforceStrategy createWorkforceStrategy() { return new WorkableTiles(); }
}
