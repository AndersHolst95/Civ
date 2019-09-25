package hotciv.framework;

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
    // Valid terrain types
    public static final String PLAINS = "plains";
    public static final String OCEANS = "ocean";
    public static final String FOREST = "forest";
    public static final String HILLS = "hills";
    public static final String MOUNTAINS = "mountain";
    // Valid production balance types
    public static final String productionFocus = "hammer";
    public static final String foodFocus = "apple";

    public interface TILE {
        int CITY_COMBAT_BONUS = 3;
        String getString();
        int getCombatBonus();

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

            public String getString() { return string; }
            public int getCombatBonus() { return combatBonus; }
        }

        class OCEANS implements TILE {
            public static final String string = GameConstants.OCEANS;
            public static final int combatBonus = 1;

            public String getString() { return string; }
            public int getCombatBonus() { return combatBonus; }
        }

        class FOREST implements TILE {
            public static final String string = GameConstants.FOREST;
            public static final int combatBonus = 2;

            public String getString() { return string; }
            public int getCombatBonus() { return combatBonus; }
        }

        class HILLS implements TILE {
            public static final String string = GameConstants.HILLS;
            public static final int combatBonus = 2;

            public String getString() { return string; }
            public int getCombatBonus() { return combatBonus; }
        }

        class MOUNTAINS implements TILE {
            public static final String string = GameConstants.MOUNTAINS;
            public static final int combatBonus = 1;

            public String getString() { return string; }
            public int getCombatBonus() { return combatBonus; }
        }
    }

    public interface UNITS {
        String getString();
        int getCost();
        int getDefStrength();
        int getMovement();
        int getAttStrength();

        static UNITS toClass(String string) {
            switch (string) {
                case GameConstants.ARCHER: return new ARCHER();
                case GameConstants.LEGION: return new LEGION();
                case GameConstants.SETTLER: return new SETTLER();
            }
            return null;
        }
        class ARCHER implements UNITS {
            public static final String string = GameConstants.ARCHER;
            public static final int attStrength = 2;
            public static final int defStrength = 3;
            public static final int movement = 1;
            public static final int cost = 10;

            public String getString() { return string; }
            public int getCost() { return cost; }
            public int getDefStrength() { return defStrength; }
            public int getMovement() { return movement; }
            public int getAttStrength() { return attStrength; }
        }

        class LEGION implements UNITS {
            public static final String string = GameConstants.LEGION;
            public static final int attStrength = 4;
            public static final int defStrength = 2;
            public static final int movement = 1;
            public static final int cost = 15;

            public String getString() { return string; }
            public int getCost() { return cost; }
            public int getDefStrength() { return defStrength; }
            public int getMovement() { return movement; }
            public int getAttStrength() { return attStrength; }
        }

        class SETTLER implements UNITS {
            public static final String string = GameConstants.SETTLER;
            public static final int attStrength = 0;
            public static final int defStrength = 3;
            public static final int movement = 1;
            public static final int cost = 30;

            public String getString() { return string; }
            public int getCost() { return cost; }
            public int getDefStrength() { return defStrength; }
            public int getMovement() { return movement; }
            public int getAttStrength() { return attStrength; }
        }
    }
}
