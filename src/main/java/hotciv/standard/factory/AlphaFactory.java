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

public class AlphaFactory implements StrategyFactory {
    public AgeStrategy getAgeStrategy() { return new ConstantAging(); }
    public VictoryStrategy getVictoryStrategy() { return new TimeVictory();}
    public LayoutStrategy getLayoutStrategy() { return new StandardLayout(); }
    public ResolveAttackStrategy getAttackStrategy() { return new AttackerWins(); }
    public UnitActionStrategy getActionStrategy() { return new NoAction(); }
}
