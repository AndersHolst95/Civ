package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.age.*;
import hotciv.framework.layout.*;
import hotciv.framework.unitAction.*;
import hotciv.framework.victoryStrategy.ConquestVictory;
import hotciv.framework.victoryStrategy.TimeVictory;
import hotciv.framework.victoryStrategy.VictoryStrategy;

/**
 * Skeleton implementation of HotCiv.
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class GameImpl implements Game {
    // Initializing global field variables
    private int worldAge = GameConstants.STARTYEAR;
    private Player currentPlayer = Player.RED;
    private Player winner = null;

    // Variable strategies
    private AgeStrategy worldAgeStrategy;
    private VictoryStrategy winCondition;
    private UnitActionStrategy unitActionStrategy;

    public GameImpl(AgeStrategy ageStrategy, VictoryStrategy victoryStrategy, UnitActionStrategy unitActionStrategy,
                    LayoutStrategy layoutStrategy){
        this.worldAgeStrategy = ageStrategy;
        this.winCondition = victoryStrategy;
        this.unitActionStrategy = unitActionStrategy;
        World.setMap(layoutStrategy.getLayout());
    }

    public GameImpl(AgeStrategy ageStrategy, VictoryStrategy victoryStrategy, UnitActionStrategy unitActionStrategy,
                    LayoutStrategy layoutStrategy, String[][] customLayout){
        this(ageStrategy, victoryStrategy, unitActionStrategy, layoutStrategy);
        World.setMap(customLayout); // sets the world layout to the custom one
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
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    /**
     * Checks if either blue or red has won the game
     * @return the new winner, who can be null
     */
    private Player checkWinner() {
        if (winCondition.checkVictory(worldAge, Player.RED))
            return Player.RED;
        if (winCondition.checkVictory(worldAge, Player.BLUE))
            return Player.BLUE;
        return null;
    }

    public int getAge() {
        return worldAge;
    }

    public boolean moveUnit(Position from, Position to) {
        return World.moveUnit(from, to, currentPlayer);
    }

    public void endOfTurn() {
        if (currentPlayer == Player.RED)
            currentPlayer = Player.BLUE;
        else { // resolve end-of-turn stuff and begin the next turn
            endOfRound();
            currentPlayer = Player.RED;
        }
    }

    /**
     * Resolves everything that happens at the end of every player's turn
     */
    private void endOfRound(){
        changeWorldAge();

        // Checking if anybody has won
        winner = checkWinner();

        // Iterating over each tile on the map
        for(int i = 0; i < GameConstants.WORLDSIZE; i++){
            for(int j = 0; j< GameConstants.WORLDSIZE; j++){
                // If the tile contains a city..
                if (getCityAt(new Position(i,j)) != null){
                    CityImpl city = ((CityImpl) getCityAt(new Position(i,j)));
                    city.addProductionValue(GameConstants.CITY_PRODUCTION_PER_TURN); // add production
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

    public void changeWorldAge() {
        worldAge = worldAgeStrategy.getNextYear(worldAge);
    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {
    }

    public void performUnitActionAt(Position p) {
    }

    public boolean doUnitAction(Position pos) {
        return unitActionStrategy.doAction(pos);
    }
}
