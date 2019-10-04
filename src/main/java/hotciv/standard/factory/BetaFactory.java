package hotciv.standard.factory;

import hotciv.standard.age.AgeStrategy;
import hotciv.standard.age.GradualAging;
import hotciv.standard.layout.LayoutStrategy;
import hotciv.standard.layout.StandardLayout;
import hotciv.standard.resolveAttack.AttackerWins;
import hotciv.standard.resolveAttack.ResolveAttackStrategy;
import hotciv.standard.unitAction.NoAction;
import hotciv.standard.unitAction.UnitActionStrategy;
import hotciv.standard.victoryStrategy.ConquestVictory;
import hotciv.standard.victoryStrategy.VictoryStrategy;
import hotciv.standard.workforce.NoWorkableTiles;
import hotciv.standard.workforce.WorkforceStrategy;

public class BetaFactory implements StrategyFactory {
    public AgeStrategy createAgeStrategy() { return new GradualAging(); }
    public VictoryStrategy createVictoryStrategy() { return new ConquestVictory();}
    public LayoutStrategy createLayoutStrategy() { return new StandardLayout(); }
    public ResolveAttackStrategy createAttackStrategy() { return new AttackerWins(); }
    public UnitActionStrategy createActionStrategy() { return new NoAction(); }
    public WorkforceStrategy createWorkforceStrategy() { return new NoWorkableTiles(); }
}
