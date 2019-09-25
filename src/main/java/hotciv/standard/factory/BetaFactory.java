package hotciv.standard.factory;

import hotciv.framework.age.AgeStrategy;
import hotciv.framework.age.GradualAging;
import hotciv.framework.layout.LayoutStrategy;
import hotciv.framework.layout.StandardLayout;
import hotciv.framework.resolveAttack.AttackerWins;
import hotciv.framework.resolveAttack.ResolveAttackStrategy;
import hotciv.framework.unitAction.NoAction;
import hotciv.framework.unitAction.UnitActionStrategy;
import hotciv.framework.victoryStrategy.ConquestVictory;
import hotciv.framework.victoryStrategy.VictoryStrategy;

public class BetaFactory implements StrategyFactory {
    public AgeStrategy getAgeStrategy() { return new GradualAging(); }
    public VictoryStrategy getVictoryStrategy() { return new ConquestVictory();}
    public LayoutStrategy getLayoutStrategy() { return new StandardLayout(); }
    public ResolveAttackStrategy getAttackStrategy() { return new AttackerWins(); }
    public UnitActionStrategy getActionStrategy() { return new NoAction(); }
}
