package hotciv.standard.unitAction;

import hotciv.framework.*;
import hotciv.standard.*;

public class ThetaAction implements UnitActionStrategy {
    public void doAction(Position pos) {
        switch (World.getUnitAt(pos).getTypeString()) {
            case GameConstants.SETTLER:
                World.setCityAt(new CityImpl(World.getUnitAt(pos).getOwner(), pos));
                World.removeUnit(pos);
                break;
            case GameConstants.ARCHER:
                ((UnitImpl) World.getUnitAt(pos)).toggleFortify();
                break;
            case GameConstants.B52:
                CityImpl city = (CityImpl) World.getCityAt(pos);
                if (city != null){
                    if(!city.decrementSize())
                        World.removeCity(pos);
                }
                if(World.getTileAt(pos).getTypeString().equals(GameConstants.FOREST))
                    World.setTypeAt(pos, GameConstants.PLAINS);
                break;
        }
    }
}
