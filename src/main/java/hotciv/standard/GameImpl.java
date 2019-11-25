package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.age.*;
import hotciv.standard.availableUnit.AvailableUnitStrategy;
import hotciv.standard.resolveAttack.ResolveAttackStrategy;
import hotciv.standard.unitAction.*;
import hotciv.standard.unitMovementDistinction.UnitMovementDistinctionStrategy;
import hotciv.standard.victoryStrategy.*;
import hotciv.standard.workforce.WorkforceStrategy;
import hotciv.standard.factory.StrategyFactory;

import java.util.ArrayList;

public class GameImpl implements Game {
    private AgeStrategy worldAgeStrategy;
    private VictoryStrategy winCondition;
    private UnitActionStrategy unitActionStrategy;
    private ResolveAttackStrategy attackStrategy;
    private WorkforceStrategy workforceStrategy;
    private UnitMovementDistinctionStrategy unitMovementDistinctionStrategy;
    private AvailableUnitStrategy availableUnitsStrategy;
    private ArrayList<GameObserver> observers = new ArrayList<>();

    public GameImpl(StrategyFactory strategy) {
        worldAgeStrategy = strategy.createAgeStrategy();
        winCondition = strategy.createVictoryStrategy();
        unitActionStrategy = strategy.createActionStrategy();
        attackStrategy = strategy.createAttackStrategy();
        workforceStrategy = strategy.createWorkforceStrategy();
        unitMovementDistinctionStrategy = strategy.createUnitMovementDistinctionStrategy();
        availableUnitsStrategy = strategy.createAvailableUnitStrategy();
        World.setMap(strategy.createLayoutStrategy().getLayout());
        GameVariables.initialize();
    }

    public GameImpl(StrategyFactory strategy, String[][] customLayout) {
        this(strategy);
        World.setMap(customLayout);
    }

    public Tile getTileAt(Position p) {
        return World.getTileAt(p);
    }

    public Unit getUnitAt(Position p) {
        return World.getUnitAt(p);
    }

    public City getCityAt(Position p) {
        return World.getCityAt(p);
    }

    public Player getPlayerInTurn() {
        return GameVariables.currentPlayer;
    }

    /**
     * Returns the winner of the game
     * @return the winner
     */
    public Player getWinner() {
        return GameVariables.winner;
    }

    /**
     * Checks and updates the winner if either blue or red has won
     */
    private void updateWinner() {
        if (winCondition.checkVictory(Player.RED))
            GameVariables.winner = Player.RED;
        else if (winCondition.checkVictory(Player.BLUE))
            GameVariables.winner = Player.BLUE;
    }

    /**
     * Returns the age of the world
     * @return the world age
     */
    public int getAge() {
        return GameVariables.age;
    }

    public UnitImpl createUnit(String type, Player owner){
        // A new unit is created, if the type is not valid, an archer is created
        return availableUnitsStrategy.validUnitType(type) ? new UnitImpl(type, owner) : new UnitImpl(GameConstants.ARCHER, owner);
    }

    public boolean setProduction(String production, CityImpl city){
         if(! availableUnitsStrategy.validUnitType(production))
             return false;
         city.setProduction(production);
         Utility.notifyWorldChange(city.getLocation(), observers);
         return true;
    }

    public boolean moveUnit(Position from, Position to) {
        boolean hasMoved = World.moveUnit(from, to, attackStrategy, unitMovementDistinctionStrategy);
        Utility.notifyWorldChange(from, observers);
        Utility.notifyWorldChange(to, observers);
        return hasMoved;
    }

    /**
     * Ends the turn for the current player. If that player is blue, endOfRound effects are resolved.
     */
    public void endOfTurn() {
        if (GameVariables.currentPlayer == Player.RED) {
            GameVariables.setCurrentPlayer(Player.BLUE);
            Utility.notifyTurnChange(GameVariables.currentPlayer, observers);
        }
        else { // resolve end-of-turn stuff and begin the next turn
            endOfRound();
            GameVariables.setCurrentPlayer(Player.RED);
            Utility.notifyTurnChange(GameVariables.currentPlayer, observers);
        }
    }

    /**
     * Resolves everything that happens at the end of every player's turn
     */
    private void endOfRound(){
        changeWorldAge();
        GameVariables.incrementRound();

        // Checking if anybody has won
        updateWinner();

        // Iterate over the map and update cities, units
        updateMapObjects();
    }

    private void updateMapObjects(){
        // Iterating over each tile on the map
        for(int i = 0; i < GameConstants.WORLDSIZE; i++){
            for(int j = 0; j< GameConstants.WORLDSIZE; j++){
                Position pos = new Position(i, j);
                // If the tile contains a city..
                if (getCityAt(pos) != null){
                    CityImpl city = ((CityImpl) getCityAt(pos));
                    workforceStrategy.workTiles(city); // Work the tiles around the city to add extra production and food
                    produceUnit(pos, city); // produce eventual units
                    Utility.notifyWorldChange(pos, observers);
                }
                // If the tile contains a unit..
                if (getUnitAt(pos) != null){
                    UnitImpl unit = ((UnitImpl) getUnitAt(pos));
                    unit.refreshMoveCount(); // refresh its movement
                    unit.setUsedAction(false);
                    Utility.notifyWorldChange(pos, observers);
                }
            }
        }
    }

    /**
     * Checks if a given city can produce a unit, and if so, tries to place it
     * @param pos The position of the city
     * @param city The city in question
     */
    private void produceUnit(Position pos, CityImpl city) {
        // check if it can produce a unit
        if (city.getProductionValue() >= city.getProductionCost()) {
            // Try to place a unit at the nearest available tile around the city, and subtracts the production if successful
            Position nearestTile = World.getNearestAvailableTile(pos, city.getProduction(), unitMovementDistinctionStrategy);
            if (World.setUnitAt(nearestTile, new UnitImpl(city.getProduction(), city.getOwner()), unitMovementDistinctionStrategy)) {
                city.addProductionValue(-city.getProductionCost());
                Utility.notifyWorldChange(nearestTile, observers);
            }
        }
    }

    /**
     * Increments the world age
     */
    private void changeWorldAge() {
        GameVariables.age = worldAgeStrategy.getNextYear(GameVariables.age);
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        CityImpl city = (CityImpl) getCityAt(p);
        if (city != null) {
            city.setWorkforceFocus(balance);
            Utility.notifyWorldChange(p, observers);
        }
    }

    public void changeProductionInCityAt(Position p, String unitType) {
        CityImpl city = (CityImpl) getCityAt(p);
        if (city != null) {
            city.setProduction(unitType);
            Utility.notifyWorldChange(p, observers);
        }
    }

    /**
     * Performs the action of the unit located at pos
     * @param pos the position of the unit
     */
    public void performUnitActionAt(Position pos) {
        if (World.getUnitAt(pos) == null)
            return;
        if (World.getUnitAt(pos).getOwner() != GameVariables.currentPlayer)
            return;
        if (((UnitImpl) World.getUnitAt(pos)).getUsedAction()) // If the action has been used this round
            return;
        ((UnitImpl) World.getUnitAt(pos)).setUsedAction(true);
        unitActionStrategy.doAction(pos);
        Utility.notifyWorldChange(pos, observers);
    }

    public boolean setUnitAt(Position pos, UnitImpl unit) {
        boolean placedUnit = World.setUnitAt(pos, unit, unitMovementDistinctionStrategy);
        if (placedUnit)
            Utility.notifyWorldChange(pos, observers);
        return placedUnit;
    }

    public void setTypeAt(Position pos, String type) {
        World.setTypeAt(pos, type);
        Utility.notifyWorldChange(pos, observers);
    }

    public void setCityAt(CityImpl city) {
        World.setCityAt(city);
        Utility.notifyWorldChange(city.getLocation(), observers);
    }

    public void addObserver(GameObserver observer){
        observers.add(observer);
    };

    public void setTileFocus(Position pos){
        Utility.notifyTileFocusChange(pos, observers);
    };

    public ArrayList<String> getAvailableUnits(){
        return availableUnitsStrategy.getAvailableUnits();
    }

    public void requestUpdate(){
        for(GameObserver observer : observers)
            observer.requestUpdate();
    }
}
