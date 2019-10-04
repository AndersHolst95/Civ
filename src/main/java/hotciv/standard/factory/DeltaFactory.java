package hotciv.standard.factory;

import hotciv.standard.age.AgeStrategy;
import hotciv.standard.age.ConstantAging;
import hotciv.standard.availableUnit.AvailableUnitStrategy;
import hotciv.standard.availableUnit.StandardUnits;
import hotciv.standard.layout.DeltaLayout;
import hotciv.standard.layout.LayoutStrategy;
import hotciv.standard.resolveAttack.AttackerWins;
import hotciv.standard.resolveAttack.ResolveAttackStrategy;
import hotciv.standard.unitAction.NoAction;
import hotciv.standard.unitAction.UnitActionStrategy;
import hotciv.standard.unitMovementDistinction.GroundOnly;
import hotciv.standard.unitMovementDistinction.UnitMovementDistinctionStrategy;
import hotciv.standard.victoryStrategy.TimeVictory;
import hotciv.standard.victoryStrategy.VictoryStrategy;
import hotciv.standard.workforce.NoWorkableTiles;
import hotciv.standard.workforce.WorkforceStrategy;

public class DeltaFactory implements StrategyFactory {
    public AgeStrategy createAgeStrategy() { return new ConstantAging(); }
    public VictoryStrategy createVictoryStrategy() { return new TimeVictory();}
    public LayoutStrategy createLayoutStrategy() { return new DeltaLayout(); }
    public ResolveAttackStrategy createAttackStrategy() { return new AttackerWins(); }
    public UnitActionStrategy createActionStrategy() { return new NoAction(); }
    public WorkforceStrategy createWorkforceStrategy() { return new NoWorkableTiles(); }
    public AvailableUnitStrategy createAvailableUnitStrategy() {return new StandardUnits(); }
    public UnitMovementDistinctionStrategy createUnitMovementDistinctionStrategy() { return new GroundOnly(); }
}
