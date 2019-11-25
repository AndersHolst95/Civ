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
    public static final String requestUpdate = game + "requestUpdate";
    public static final String getAvailableUnits = game + "getAvailableUnits";

    // City methods
    public static final String city = "city_";
    public static final String getOwnerCity = city + "getOwner";
    public static final String getSize = city + "getSize";
    public static final String getTreasury = city + "getTreasury";
    public static final String getProduction = city + "getProduction";
    public static final String getWorkforceFocus = city + "getWorkforceFocus";
    public static final String getFood = city + "getFood";
    public static final String getLocation = city + "getLocation";
    public static final String getProductionValue = city + "getProductionValue";
    public static final String getProductionCost = city + "getProductionCost";
    public static final String decrementSize = city + "decrementSize";
    public static final String addProductionValue = city + "addProductionValue";
    public static final String setProduction = city + "setProduction";
    public static final String setOwner = city + "setOwner";
    public static final String resetFood = city + "resetFood";
    public static final String increaseSize = city + "increaseSize";
    public static final String addFood = city + "addFood";
    public static final String setWorkforceFocus = city + "setWorkforceFocus";

    // Unit methods
    public static final String unit = "unit_";
    public static final String getTypeStringUnit = unit + "getTypeString";
    public static final String getOwnerUnit = unit + "getOwner";
    public static final String getMoveCount = unit + "getMoveCount";
    public static final String getDefensiveStrength = unit + "getDefensiveStrength";
    public static final String getAttackingStrength = unit + "getAttackingStrength";
    public static final String refreshMoveCount = unit + "refreshMoveCount";
    public static final String setMoveCount = unit + "setMoveCount";
    public static final String toggleFortify = unit + "toggleFortify";
    public static final String setUsedAction = unit + "setUsedAction";
    public static final String getUsedAction = unit + "getUsedAction";

    // Tile methods
    public static final String tile = "tile_";
    public static final String getTypeStringTile = tile + "getTypeString";
    public static final String setType = tile + "setType";
    public static final String setCity = tile + "setCity";
    public static final String setUnit = tile + "setUnit";
    public static final String getUnit = tile + "getUnit";
    public static final String getCity = tile + "getCity";
}
