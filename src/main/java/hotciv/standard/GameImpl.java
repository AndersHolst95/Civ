package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.age.*;
import hotciv.framework.layout.*;
import hotciv.framework.unitAction.*;
import hotciv.framework.victoryCondition.*;

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
    // ---------- Initialize the world ---------- \\
    private AgeStrategy worldAgeStrategy;
    public int worldAge = GameConstants.STARTYEAR;
    public VictoryStrategy winCondition;
    public Player winner = null;

    private Player currentPlayer = Player.RED;
    private UnitActionStrategy actions;
    // ------------------------------------------ \\

    public GameImpl(String version){
        LayoutStrategy layout = new StandardLayout();
        AgeStrategy age = new ConstantAging();
        UnitActionStrategy actions = new NoAction();
        VictoryStrategy winCondition = new TimeVictory();

        switch (version) {
            case "beta":
                age = new GradualAging();
                winCondition = new ConquestVictory();
                break;
            case "gamma":
                actions = new GammaAction();
                break;
            case "delta":
                layout = new DeltaLayout();
                break;
            default:
        }
        this.actions = actions;
        World.setMap(layout.getLayout());
        this.worldAgeStrategy = age;
        this.winCondition = winCondition;
    }

    public GameImpl(String version, String[][] customLayout){
        this(version);
        if (version.equals("delta"))
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

    public boolean checkWinner(Player player) {
        return winCondition.checkVictory(worldAge, player);
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
        if(checkWinner(Player.RED))
            winner = Player.RED;
        if (checkWinner(Player.BLUE))
            winner = Player.BLUE;

        // Iterating over each tile on the map
        for(int i = 0; i < GameConstants.WORLDSIZE; i++){
            for(int j = 0; j< GameConstants.WORLDSIZE; j++){
                // If the tile contains a city..
                if (getCityAt(new Position(i,j)) != null){
                    CityImpl city = ((CityImpl) getCityAt(new Position(i,j)));
                    city.addProductionValue(6); // add production

                    // check if it can produce a unit
                    if (city.getProductionValue() >= city.getProductionCost()) {
                        if (World.setUnitAt(World.getNearestAvailableTile(new Position(i, j)), new UnitImpl(city.getProduction(), city.getOwner())))
                            city.addProductionValue(-city.getProductionCost());
                    }
                }
                // If the tile contains a unit..
                if (getUnitAt(new Position(i, j)) != null){
                    UnitImpl unit = ((UnitImpl) getUnitAt(new Position(i, j)));
                    unit.refreshMoveCount(); // refresh its movement
                }
            }
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
        actions.doAction(getUnitAt(pos));
        return true;
    }
}
