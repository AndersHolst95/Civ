package hotciv.standard.factory;

import hotciv.standard.age.AgeStrategy;
import hotciv.standard.age.ConstantAging;
import hotciv.standard.availableUnit.AvailableUnitStrategy;
import hotciv.standard.availableUnit.B52Expansion;
import hotciv.standard.availableUnit.StandardUnits;
import hotciv.standard.layout.LayoutStrategy;
import hotciv.standard.layout.StandardLayout;
import hotciv.standard.resolveAttack.AttackerWins;
import hotciv.standard.resolveAttack.ResolveAttackStrategy;
import hotciv.standard.unitAction.GammaAction;
import hotciv.standard.unitAction.NoAction;
import hotciv.standard.unitAction.ThetaAction;
import hotciv.standard.unitAction.UnitActionStrategy;
import hotciv.standard.unitMovementDistinction.GroundAndAir;
import hotciv.standard.unitMovementDistinction.GroundOnly;
import hotciv.standard.unitMovementDistinction.UnitMovementDistinctionStrategy;
import hotciv.standard.victoryStrategy.TimeVictory;
import hotciv.standard.victoryStrategy.VictoryStrategy;
import hotciv.standard.victoryStrategy.ZetaVictory;
import hotciv.standard.workforce.NoWorkableTiles;
import hotciv.standard.workforce.WorkforceStrategy;

public class ThetaFactory implements StrategyFactory {
    public AgeStrategy createAgeStrategy() { return new ConstantAging(); }
    public VictoryStrategy createVictoryStrategy() { return new TimeVictory();}
    public LayoutStrategy createLayoutStrategy() { return new StandardLayout(); }
    public ResolveAttackStrategy createAttackStrategy() { return new AttackerWins(); }
    public UnitActionStrategy createActionStrategy() { return new ThetaAction(); }
    public WorkforceStrategy createWorkforceStrategy() { return new NoWorkableTiles(); }
    public AvailableUnitStrategy createAvailableUnitStrategy() {return new B52Expansion(); }
    public UnitMovementDistinctionStrategy createUnitMovementDistinctionStrategy() { return new GroundAndAir(); }
}
