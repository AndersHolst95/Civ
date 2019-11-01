package hotciv.stub;

import hotciv.framework.*;

import java.util.*;

public class StubGame2 implements Game {

  private Position pos_archer_red;
  private Position pos_legion_blue;
  private Position pos_settler_red;
  private Position pos_bomb_red;
  private Unit red_archer;
  protected GameObserver gameObserver;
  private Player inTurn;
  protected Map<Position, Tile> world;

  public Unit getUnitAt(Position p) {
    if (p.equals(pos_archer_red)) {
      return red_archer;
    }
    if (p.equals(pos_settler_red)) {
      return new StubUnit(GameConstants.SETTLER, Player.RED);
    }
    if (p.equals(pos_legion_blue)) {
      return new StubUnit(GameConstants.LEGION, Player.BLUE);
    }
    if (p.equals(pos_bomb_red)) {
      return new StubUnit(ThetaConstants.B52, Player.RED);
    }
    return null;
  }

  // Stub only allows moving red archer
  public boolean moveUnit(Position from, Position to) {
    System.out.println("-- StubGame2 / moveUnit called: " + from + "->" + to);
    if (from.equals(pos_archer_red)) {
      pos_archer_red = to;
    }
    // notify our observer(s) about the changes on the tiles
    gameObserver.worldChangedAt(from);
    gameObserver.worldChangedAt(to);
    return true;
  }

  public void endOfTurn() {
    System.out.println("-- StubGame2 / endOfTurn called.");
    inTurn = (getPlayerInTurn() == Player.RED ?
            Player.BLUE :
            Player.RED);
    // no age increments
    gameObserver.turnEnds(inTurn);
  }

  public Player getPlayerInTurn() {
    return inTurn;
  }

  // === Observer handling ===
  // observer list is only a single one...
  public void addObserver(GameObserver observer) {
    gameObserver = observer;
  }

  public StubGame2() {
    defineWorld(1);
    // AlphaCiv configuration
    pos_archer_red = new Position(2, 0);
    pos_legion_blue = new Position(3, 2);
    pos_settler_red = new Position(4, 3);
    pos_bomb_red = new Position(6, 4);

    // the only one I need to store for this stub
    red_archer = new StubUnit(GameConstants.ARCHER, Player.RED);

    inTurn = Player.RED;
  }

  // A simple implementation to draw the map of DeltaCiv
  public Tile getTileAt(Position p) {
    return world.get(p);
  }

  /**
   * define the world.
   *
   * @param worldType 1 gives one layout while all other
   *                  values provide a second world layout.
   */
  protected void defineWorld(int worldType) {
    world = new HashMap<Position, Tile>();
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        Position p = new Position(r, c);
        world.put(p, new StubTile(GameConstants.PLAINS));
      }
    }
  }

  // TODO: Add more stub behaviour to test MiniDraw updating
  public City getCityAt(Position p) {
    return null;
  }

  public Player getWinner() {
    return null;
  }

  public int getAge() {
    return 0;
  }

  public void changeWorkForceFocusInCityAt(Position p, String balance) {
  }

  public void changeProductionInCityAt(Position p, String unitType) {
  }

  public void performUnitActionAt(Position p) {
  }

  public void setTileFocus(Position pos) {
    // TODO: setTileFocus implementation pending.
    System.out.println("-- StubGame2 / setTileFocus called.");
    gameObserver.tileFocusChangedAt(pos);
  }

  private void notifyWorldChange(Position pos) {
    gameObserver.worldChangedAt(pos);
  }

  private void notifyTurnChange(Player nextPlayer) {
    gameObserver.turnEnds(nextPlayer);
  }

  private void notifyTileFocusChange(Position pos) {
    gameObserver.tileFocusChangedAt(pos);
  }

  class StubUnit implements Unit {
    private String type;
    private Player owner;

    public StubUnit(String type, Player owner) {
      this.type = type;
      this.owner = owner;
    }

    public String getTypeString() {
      return type;
    }

    public Player getOwner() {
      return owner;
    }

    public int getMoveCount() {
      return 1;
    }

    public int getDefensiveStrength() {
      return 0;
    }

    public int getAttackingStrength() {
      return 0;
    }
  }
}