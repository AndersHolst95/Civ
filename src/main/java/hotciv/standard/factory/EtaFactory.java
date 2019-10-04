package hotciv.standard.factory;

import hotciv.standard.age.AgeStrategy;
import hotciv.standard.age.ConstantAging;
import hotciv.standard.layout.LayoutStrategy;
import hotciv.standard.layout.StandardLayout;
import hotciv.standard.resolveAttack.AttackerWins;
import hotciv.standard.resolveAttack.ResolveAttackStrategy;
import hotciv.standard.unitAction.NoAction;
import hotciv.standard.unitAction.UnitActionStrategy;
import hotciv.standard.victoryStrategy.TimeVictory;
import hotciv.standard.victoryStrategy.VictoryStrategy;
import hotciv.standard.workforce.WorkableTiles;
import hotciv.standard.workforce.WorkforceStrategy;

public class EtaFactory implements StrategyFactory{
    public AgeStrategy createAgeStrategy() { return new ConstantAging(); }
    public VictoryStrategy createVictoryStrategy() { return new TimeVictory();}
    public LayoutStrategy createLayoutStrategy() { return new StandardLayout(); }
    public ResolveAttackStrategy createAttackStrategy() { return new AttackerWins(); }
    public UnitActionStrategy createActionStrategy() { return new NoAction(); }
    public WorkforceStrategy createWorkforceStrategy() { return new WorkableTiles(); }
}
