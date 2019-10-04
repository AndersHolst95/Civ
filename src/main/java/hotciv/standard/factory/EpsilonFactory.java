package hotciv.standard.factory;

import hotciv.standard.age.AgeStrategy;
import hotciv.standard.age.ConstantAging;
import hotciv.standard.availableUnit.AvailableUnitStrategy;
import hotciv.standard.availableUnit.StandardUnits;
import hotciv.standard.layout.LayoutStrategy;
import hotciv.standard.layout.StandardLayout;
import hotciv.standard.random.RandomStrategy;
import hotciv.standard.resolveAttack.ActualCombat;
import hotciv.standard.resolveAttack.ResolveAttackStrategy;
import hotciv.standard.unitAction.NoAction;
import hotciv.standard.unitAction.UnitActionStrategy;
import hotciv.standard.unitMovementDistinction.GroundOnly;
import hotciv.standard.unitMovementDistinction.UnitMovementDistinctionStrategy;
import hotciv.standard.victoryStrategy.ThreeCombatVictories;
import hotciv.standard.victoryStrategy.VictoryStrategy;
import hotciv.standard.workforce.NoWorkableTiles;
import hotciv.standard.workforce.WorkforceStrategy;

public class EpsilonFactory implements StrategyFactory {
    private ActualCombat combat;

    public AgeStrategy createAgeStrategy() { return new ConstantAging(); }
    public VictoryStrategy createVictoryStrategy() { return new ThreeCombatVictories();}
    public LayoutStrategy createLayoutStrategy() { return new StandardLayout(); }
    public ResolveAttackStrategy createAttackStrategy() { return combat; }
    public UnitActionStrategy createActionStrategy() { return new NoAction(); }
    public WorkforceStrategy createWorkforceStrategy() { return new NoWorkableTiles(); }
    public AvailableUnitStrategy createAvailableUnitStrategy() {return new StandardUnits(); }
    public UnitMovementDistinctionStrategy createUnitMovementDistinctionStrategy() { return new GroundOnly(); }

    public EpsilonFactory() {
        combat = new ActualCombat();
    }
    public EpsilonFactory(RandomStrategy random) {
        combat = new ActualCombat(random);
    }
}
