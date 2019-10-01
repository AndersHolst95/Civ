package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.age.*;
import hotciv.framework.layout.*;
import hotciv.framework.resolveAttack.ResolveAttackStrategy;
import hotciv.framework.unitAction.*;
import hotciv.framework.victoryStrategy.*;
import hotciv.framework.workforce.WorkforceStrategy;
import hotciv.standard.factory.StrategyFactory;

public class GameImpl implements Game {
    private AgeStrategy worldAgeStrategy;
    private VictoryStrategy winCondition;
    private UnitActionStrategy unitActionStrategy;
    private ResolveAttackStrategy attackStrategy;
    private WorkforceStrategy workforceStrategy;

    public GameImpl(StrategyFactory strategy) {
        worldAgeStrategy = strategy.getAgeStrategy();
        winCondition = strategy.getVictoryStrategy();
        unitActionStrategy = strategy.getActionStrategy();
        attackStrategy = strategy.getAttackStrategy();
        workforceStrategy = strategy.getWorkforceStrategy();
        World.setMap(strategy.getLayoutStrategy().getLayout());
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

    public boolean moveUnit(Position from, Position to) {
        return World.moveUnit(from, to, attackStrategy);
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
                    workforceStrategy.workTiles(new Position(i, j)); // Work the tiles around the city to add extra production and food
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
            if (World.setUnitAt(World.getNearestAvailableTile(new Position(row, col)), new UnitImpl(city.getProduction(), city.getOwner())))
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
        return World.setUnitAt(pos, unit);
    }

    public void setTypeAt(Position pos, String type) {
        World.setTypeAt(pos, type);
    }

    public void setCityAt(Position pos, CityImpl city) {
        World.setCityAt(pos, city);
    }
}
