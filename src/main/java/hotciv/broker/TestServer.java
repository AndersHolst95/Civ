package hotciv.broker;

import hotciv.framework.Player;

public class TestServer {
    private static String ip = "10.192.138.211";
    private static Client client;

    public static void main(String[] args) {
        int counter = 0;
        client = new Client(ip, 2800);

        // CITY //
        client.gameProxy.getCityAt(null);
        if (client.cityProxy.getProduction("city").equals("dragon"))
            counter++;
        if (client.cityProxy.getOwner("city").equals(Player.YELLOW))
            counter++;
        if (client.cityProxy.getWorkforceFocus("city").equals("vandland"))
            counter++;
        if (client.cityProxy.getTreasury("city") == 119)
            counter++;
        if (client.cityProxy.getSize("city") == 82)
            counter++;

        // UNIT //
        client.gameProxy.getUnitAt(null);
        if (client.unitProxy.getMoveCount("unit") == 9)
            counter++;
        if (client.unitProxy.getAttackingStrength("unit") == 1337)
            counter++;
        if (client.unitProxy.getDefensiveStrength("unit") == 64)
            counter++;
        if (client.unitProxy.getTypeString("unit").equals("BigBoy"))
            counter++;
        if (client.unitProxy.getOwner("unit").equals(Player.GREEN))
            counter++;

        // TILE //
        client.gameProxy.getTileAt(null);
        if (client.tileProxy.getTypeString("tile").equals("oasis"))
            counter++;

        System.out.println(counter + "/11 test successful");
    }
}