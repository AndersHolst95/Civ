package hotciv.standard.factory;

import hotciv.framework.age.AgeStrategy;
import hotciv.framework.age.ConstantAging;
import hotciv.framework.layout.LayoutStrategy;
import hotciv.framework.layout.StandardLayout;
import hotciv.framework.resolveAttack.AttackerWins;
import hotciv.framework.resolveAttack.ResolveAttackStrategy;
import hotciv.framework.unitAction.NoAction;
import hotciv.framework.unitAction.UnitActionStrategy;
import hotciv.framework.victoryStrategy.TimeVictory;
import hotciv.framework.victoryStrategy.VictoryStrategy;
import hotciv.framework.workforce.NoWorkableTiles;
import hotciv.framework.workforce.WorkableTiles;
import hotciv.framework.workforce.WorkforceStrategy;

public class EtaFactory implements StrategyFactory{
    public AgeStrategy createAgeStrategy() { return new ConstantAging(); }
    public VictoryStrategy createVictoryStrategy() { return new TimeVictory();}
    public LayoutStrategy createLayoutStrategy() { return new StandardLayout(); }
    public ResolveAttackStrategy createAttackStrategy() { return new AttackerWins(); }
    public UnitActionStrategy createActionStrategy() { return new NoAction(); }
    public WorkforceStrategy createWorkforceStrategy() { return new WorkableTiles(); }
}
