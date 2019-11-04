package hotciv.standard.workforce;

import hotciv.framework.*;
import hotciv.standard.CityImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class WorkableTiles implements WorkforceStrategy{
    public void workTiles(CityImpl city) {
        int food = 1;
        int production = 1;
        String focus = city.getWorkforceFocus();
        ArrayList<Position> tileList = Utility.nearestTileList(city.getLocation());
        tileList.remove(0);

        switch (focus){
            case GameConstants.foodFocus:
                Comparator cf = new Comparator<Position>() {
                    @Override
                    public int compare(Position o1, Position o2) {
                        int foodP1 = GameConstants.TILE.toClass(World.getTileAt(o1).getTypeString()).getFood();
                        int foodP2 = GameConstants.TILE.toClass(World.getTileAt(o2).getTypeString()).getFood();
                        return foodP2 - foodP1;
                    }
                };
                Collections.sort(tileList, cf);
                break;
            case GameConstants.productionFocus:
                Comparator cp = new Comparator<Position>() {
                    @Override
                    public int compare(Position o1, Position o2) {
                        int prodP1 = GameConstants.TILE.toClass(World.getTileAt(o1).getTypeString()).getProduction();
                        int prodP2 = GameConstants.TILE.toClass(World.getTileAt(o2).getTypeString()).getProduction();
                        return prodP2 - prodP1;
                    }
                };
                Collections.sort(tileList, cp);
                break;
        }
        for (int i = 0; (i < city.getSize() - 1) && i < tileList.size(); i++) {
            food += GameConstants.TILE.toClass(World.getTileAt(tileList.get(i)).getTypeString()).getFood();
            production += GameConstants.TILE.toClass(World.getTileAt(tileList.get(i)).getTypeString()).getProduction();
        }

        city.addProductionValue(production);
        city.addFood(food);

        growCity(city);
    }
    public void growCity(CityImpl city) {
        if(city.getFood() > 5 + city.getSize() * 3 ){
            city.resetFood();
            city.increaseSize();
        }
    }
}
