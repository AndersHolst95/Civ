package hotciv.framework;

import java.util.HashMap;

public class GameVariables {
    public static int round;
    public static HashMap<Player, Integer> combatVictories;
    public static int age;
    public static Player winner;
    public static Player currentPlayer;

    public static void initialize(){
        round = 0;
        combatVictories = new HashMap<Player, Integer>();
        resetVictories();
        age = GameConstants.STARTYEAR;
        winner = null;
        currentPlayer = Player.RED;
    }

    public static void incrementRound() {
        GameVariables.round++;
    }

    public static void setAge(int age) {
        GameVariables.age = age;
    }

    public static void incrementVictory(Player player){
        int wins = GameVariables.combatVictories.get(player);
        GameVariables.combatVictories.put(player, wins + 1);
    }

    public static void setWinner(Player winner) {
        GameVariables.winner = winner;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        GameVariables.currentPlayer = currentPlayer;
    }

    public static int getCombatVictories(Player player) {
        return combatVictories.get(player);
    }

    private static void resetVictories(){
        combatVictories.put(Player.RED, 0);
        combatVictories.put(Player.BLUE, 0);
        combatVictories.put(Player.YELLOW, 0);
        combatVictories.put(Player.GREEN, 0);
    }
}

