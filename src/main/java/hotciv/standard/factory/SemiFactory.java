package hotciv.standard.factory;


import hotciv.standard.age.AgeStrategy;
import hotciv.standard.age.GradualAging;
import hotciv.standard.availableUnit.AvailableUnitStrategy;
import hotciv.standard.availableUnit.B52Expansion;
import hotciv.standard.availableUnit.StandardUnits;
import hotciv.standard.layout.DeltaLayout;
import hotciv.standard.layout.FractalLayout;
import hotciv.standard.layout.LayoutStrategy;
import hotciv.standard.resolveAttack.ActualCombat;
import hotciv.standard.resolveAttack.ResolveAttackStrategy;
import hotciv.standard.unitAction.GammaAction;
import hotciv.standard.unitAction.UnitActionStrategy;
import hotciv.standard.unitMovementDistinction.GroundAndAir;
import hotciv.standard.unitMovementDistinction.GroundOnly;
import hotciv.standard.unitMovementDistinction.UnitMovementDistinctionStrategy;
import hotciv.standard.victoryStrategy.ThreeCombatVictories;
import hotciv.standard.victoryStrategy.VictoryStrategy;
import hotciv.standard.workforce.WorkableTiles;
import hotciv.standard.workforce.WorkforceStrategy;

public class SemiFactory implements StrategyFactory {
    public AgeStrategy createAgeStrategy() { return new GradualAging(); }
    public VictoryStrategy createVictoryStrategy() { return new ThreeCombatVictories();}
    public LayoutStrategy createLayoutStrategy() { return new DeltaLayout(); }
    public ResolveAttackStrategy createAttackStrategy() { return new ActualCombat(); }
    public UnitActionStrategy createActionStrategy() { return new GammaAction(); }
    public WorkforceStrategy createWorkforceStrategy() { return new WorkableTiles(); }
    public AvailableUnitStrategy createAvailableUnitStrategy() {return new B52Expansion(); }
    public UnitMovementDistinctionStrategy createUnitMovementDistinctionStrategy() { return new GroundAndAir(); }
}
