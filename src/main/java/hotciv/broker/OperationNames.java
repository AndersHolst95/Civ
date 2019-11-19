package hotciv.broker;

import hotciv.framework.City;

public class OperationNames {
    // Game Methods
    public static final String game = "game_";
    public static final String getTileAt = game + "getTileAt";
    public static final String getUnitAt = game + "getUnitAt";
    public static final String getCityAt = game + "getCityAt";
    public static final String getPlayerInTurn = game + "getPlayerInTurn";
    public static final String getWinner = game + "getWinner";
    public static final String getAge = game + "getAge";
    public static final String moveUnit = game + "moveUnit";
    public static final String endOfTurn = game + "endOfTurn";
    public static final String changeWorkForceFocusInCityAt = game + "changeWorkForceFocusInCityAt";
    public static final String changeProductionInCityAt = game + "changeProductionInCityAt";
    public static final String performUnitActionAt = game + "performUnitActionAt";
    public static final String setTileFocus = game + "setTileFocus";
    public static final String addObserver = game + "addObserver";

    // City methods
    public static final String city = "city_";
    public static final String getOwnerCity = city + "getOwner";
    public static final String getSize = city + "getSize";
    public static final String getTreasury = city + "getTreasury";
    public static final String getProduction = city + "getProduction";
    public static final String getWorkforceFocus = city + "getWorkforceFocus";

    // Unit methods
    public static final String unit = "unit_";
    public static final String getTypeStringUnit = unit + "getTypeString";
    public static final String getOwnerUnit = unit + "getOwner";
    public static final String getMoveCount = unit + "getMoveCount";
    public static final String getDefensiveStrength = unit + "getDefensiveStrength";
    public static final String getAttackingStrength = unit + "getAttackingStrength";

    // Tile methods
    public static final String tile = "tile_";
    public static final String getTypeStringTile = tile + "getTypeString";


}
