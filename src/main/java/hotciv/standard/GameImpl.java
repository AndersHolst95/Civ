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

public class GameImpl implements Game {
    private AgeStrategy worldAgeStrategy;
    private VictoryStrategy winCondition;
    private UnitActionStrategy unitActionStrategy;
    private ResolveAttackStrategy attackStrategy;
    private WorkforceStrategy workforceStrategy;
    private UnitMovementDistinctionStrategy unitMovementDistinctionStrategy;
    private AvailableUnitStrategy availableUnitsStrategy;

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
         return true;
    }

    public boolean moveUnit(Position from, Position to) {
        return World.moveUnit(from, to, attackStrategy, unitMovementDistinctionStrategy);
    }

    /**
     * Ends the turn for the current player. If that player is blue, endOfRound effects are resolved.
     */
    public void endOfTurn() {
        if (GameVariables.currentPlayer == Player.RED)
            GameVariables.setCurrentPlayer(Player.BLUE);
        else { // resolve end-of-turn stuff and begin the next turn
            endOfRound();
            GameVariables.setCurrentPlayer(Player.RED);
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
                // If the tile contains a city..
                if (getCityAt(new Position(i,j)) != null){
                    CityImpl city = ((CityImpl) getCityAt(new Position(i,j)));
                    workforceStrategy.workTiles(city); // Work the tiles around the city to add extra production and food
                    produceUnit(i, j, city); // produce eventual units
                }
                // If the tile contains a unit..
                if (getUnitAt(new Position(i, j)) != null){
                    UnitImpl unit = ((UnitImpl) getUnitAt(new Position(i, j)));
                    unit.refreshMoveCount(); // refresh its movement
                }
            }
        }
    }

    /**
     * Checks if a given city can produce a unit, and if so, tries to place it
     * @param row The row index of the position
     * @param col The column index of the position
     * @param city The city in question
     */
    private void produceUnit(int row, int col, CityImpl city) {
        // check if it can produce a unit
        if (city.getProductionValue() >= city.getProductionCost()) {
            // Try to place a unit at the nearest available tile around the city, and subtracts the production if successful
            if (World.setUnitAt(World.getNearestAvailableTile(new Position(row, col), city.getProduction(), unitMovementDistinctionStrategy),
                    new UnitImpl(city.getProduction(), city.getOwner()), unitMovementDistinctionStrategy))
                city.addProductionValue(-city.getProductionCost());
        }
    }

    /**
     * Increments the world age
     */
    private void changeWorldAge() {
        GameVariables.age = worldAgeStrategy.getNextYear(GameVariables.age);
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
    }

    /**
     * Performs the action of the unit located at pos
     * @param pos the position of the unit
     */
    public void performUnitActionAt(Position pos) {
        unitActionStrategy.doAction(pos);
    }

    public boolean setUnitAt(Position pos, UnitImpl unit) {
        return World.setUnitAt(pos, unit, unitMovementDistinctionStrategy);
    }

    public void setTypeAt(Position pos, String type) {
        World.setTypeAt(pos, type);
    }

    public void setCityAt(CityImpl city) {
        World.setCityAt(city);
    }
}
