package hotciv.framework;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.*;

/**
 * Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.
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
public class GameConstants {
    // The size of the world is set permanently to a 16x16 grid
    public static final int CITY_PRODUCTION_PER_TURN = 6;
    public static final int WORLDSIZE = 16;
    public static final int STARTYEAR = -4000;  // This is the starting year for all games of civ
    // Valid unit types
    public static final String ARCHER = "archer";
    public static final String LEGION = "legion";
    public static final String SETTLER = "settler";
    public static final String B52 = "b52";
    // Valid terrain types
    public static final String PLAINS = "plains";
    public static final String OCEANS = "ocean";
    public static final String FOREST = "forest";
    public static final String HILLS = "hills";
    public static final String MOUNTAINS = "mountain";
    // Valid production balance types
    public static final String productionFocus = "hammer";
    public static final String foodFocus = "apple";

    public static class MAP_SYMBOLS {
        public static final Map<Character, String> unitSymbols = new HashMap<Character, String>(){{
                put('a', ARCHER);
                put('l', LEGION);
                put('s', SETTLER);
                put('b', B52);
            }};

        public static final Map<Character, String> tileSymbols = new HashMap<Character, String>(){{
                put('p', TILE.PLAINS.string);
                put('o', TILE.OCEANS.string);
                put('m', TILE.MOUNTAINS.string);
                put('f', TILE.FOREST.string);
                put('h', TILE.HILLS.string);
            }};

        public static final Map<Character, Player> playerSymbols = new HashMap<Character, Player>(){
            {
                put('1', Player.RED);
                put('2', Player.BLUE);
                put('3', Player.GREEN);
                put('4', Player.YELLOW);
            }};
    }

    public interface TILE {
        int CITY_COMBAT_BONUS = 3;
        String getString();
        int getCombatBonus();
        int getFood();
        int getProduction();

        static TILE toClass(String string) {
            switch (string) {
                case GameConstants.PLAINS: return new PLAINS();
                case GameConstants.OCEANS: return new OCEANS();
                case GameConstants.FOREST: return new FOREST();
                case GameConstants.HILLS: return new HILLS();
                case GameConstants.MOUNTAINS: return new MOUNTAINS();
            }
            return null;
        }

        class PLAINS implements TILE {
            public static final String string = GameConstants.PLAINS;
            public static final int combatBonus = 1;
            public static final int food = 3;
            public static final int production = 0;

            public String getString() { return string; }
            public int getCombatBonus() { return combatBonus; }
            public int getFood() {return food;}
            public int getProduction() {return production;}
        }

        class OCEANS implements TILE {
            public static final String string = OCEANS;
            public static final int combatBonus = 1;
            public static final int food = 1;
            public static final int production = 0;

            public String getString() { return string; }
            public int getCombatBonus() { return combatBonus; }
            public int getFood() {return food;}
            public int getProduction() {return production;}
        }

        class FOREST implements TILE {
            public static final String string = FOREST;
            public static final int combatBonus = 2;
            public static final int food = 0;
            public static final int production = 3;

            public String getString() { return string; }
            public int getCombatBonus() { return combatBonus; }
            public int getFood() {return food;}
            public int getProduction() {return production;}
        }

        class HILLS implements TILE {
            public static final String string = HILLS;
            public static final int combatBonus = 2;
            public static final int food = 0;
            public static final int production = 2;

            public String getString() { return string; }
            public int getCombatBonus() { return combatBonus; }
            public int getFood() {return food;}
            public int getProduction() {return production;}
        }

        class MOUNTAINS implements TILE {
            public static final String string = MOUNTAINS;
            public static final int combatBonus = 1;
            public static final int food = 0;
            public static final int production = 1;

            public String getString() { return string; }
            public int getCombatBonus() { return combatBonus; }
            public int getFood() {return food;}
            public int getProduction() {return production;}
        }
    }

    public interface UNITS {
        String GROUND = "ground";
        String FLYING = "flying";
        String SAILING = "sail";

        String getString();
        int getCost();
        int getDefStrength();
        int getMovement();
        int getAttStrength();
        String getMovementCategory();

        static UNITS toClass(String string) {
            switch (string) {
                case GameConstants.ARCHER: return new ARCHER();
                case GameConstants.LEGION: return new LEGION();
                case GameConstants.SETTLER: return new SETTLER();
                case GameConstants.B52: return new B52();
            }
            return null;
        }
        class ARCHER implements UNITS {
            public static final String string = ARCHER;
            public static final int attStrength = 2;
            public static final int defStrength = 3;
            public static final int movement = 1;
            public static final int cost = 10;
            public static final String movementCategory = GROUND;

            public String getString() { return string; }
            public int getCost() { return cost; }
            public int getDefStrength() { return defStrength; }
            public int getMovement() { return movement; }
            public int getAttStrength() { return attStrength; }
            public String getMovementCategory() { return movementCategory; }
        }

        class LEGION implements UNITS {
            public static final String string = LEGION;
            public static final int attStrength = 4;
            public static final int defStrength = 2;
            public static final int movement = 1;
            public static final int cost = 15;
            public static final String movementCategory = GROUND;

            public String getString() { return string; }
            public int getCost() { return cost; }
            public int getDefStrength() { return defStrength; }
            public int getMovement() { return movement; }
            public int getAttStrength() { return attStrength; }
            public String getMovementCategory() { return movementCategory; }
        }

        class SETTLER implements UNITS {
            public static final String string = SETTLER;
            public static final int attStrength = 0;
            public static final int defStrength = 3;
            public static final int movement = 1;
            public static final int cost = 30;
            public static final String movementCategory = GROUND;

            public String getString() { return string; }
            public int getCost() { return cost; }
            public int getDefStrength() { return defStrength; }
            public int getMovement() { return movement; }
            public int getAttStrength() { return attStrength; }
            public String getMovementCategory() { return movementCategory; }
        }

        class B52 implements UNITS {
            public static final String string = B52;
            public static final int attStrength = 1;
            public static final int defStrength = 8;
            public static final int movement = 2;
            public static final int cost = 60;
            public static final String movementCategory = FLYING;

            public String getString() { return string; }
            public int getCost() { return cost; }
            public int getDefStrength() { return defStrength; }
            public int getMovement() { return movement; }
            public int getAttStrength() { return attStrength; }
            public String getMovementCategory() { return movementCategory; }
        }
    }
}
