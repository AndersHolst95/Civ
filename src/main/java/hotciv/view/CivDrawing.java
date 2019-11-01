package hotciv.view;

import hotciv.framework.*;

import java.awt.*;
import java.util.*;
import java.util.List;

import minidraw.framework.*;
import minidraw.standard.*;

public class CivDrawing implements Drawing, GameObserver {
  
  protected Drawing delegate;
  /** store all moveable figures visible in this drawing = units */
  protected Map<Unit, UnitFigure> unitFigureMap;
  protected Map<City, CityFigure> cityFigureMap;

  /** the Game instance that this CivDrawing is going to render units
   * from */
  protected Game game;

  HashMap<Player, String> playerHashMap = new HashMap<Player, String>(){{
    put(Player.RED, "red");
    put(Player.BLUE, "blue");
    put(Player.GREEN, "green");
    put(Player.YELLOW, "yellow");
  }};
  
  public CivDrawing( DrawingEditor editor, Game game ) {
    super();
    this.delegate = new StandardDrawing();
    this.game = game;
    this.unitFigureMap = new HashMap<>();
    this.cityFigureMap = new HashMap<>();

    // register this unit drawing as listener to any game state
    // changes...
    game.addObserver(this);
    // ... and build up the set of figures associated with
    // units in the game.
    defineUnitMap();
    // and the set of 'icons' in the status panel
    defineIcons();
  }
  
  /** The CivDrawing should not allow client side
   * units to add and manipulate figures; only figures
   * that renders game objects are relevant, and these
   * should be handled by observer events from the game
   * instance. Thus this method is 'killed'.
   */
  public Figure add(Figure arg0) {
    throw new RuntimeException("Should not be used...");
  }


  /** erase the old list of units, and build a completely new
   * one from scratch by iterating over the game world and add
   * Figure instances for each unit in the world.
   */
  protected void defineUnitMap() {
    // ensure no units of the old list are accidental in
    // the selection!
    clearSelection();

    // remove all unit figures in this drawing
    removeAllUnitFigures();

    // iterate world, and create a unit figure for
    // each unit in the game world, as well as
    // create an association between the unit and
    // the unitFigure in 'unitFigureMap'.
    Position p;
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        p = new Position(r,c);
        Unit unit = game.getUnitAt(p);
        if ( unit != null ) {
          String type = unit.getTypeString();
          // convert the unit's Position to (x,y) coordinates
          Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                                   GfxConstants.getYFromRow(p.getRow()) );
          UnitFigure unitFigure =
            new UnitFigure( type, point, unit );
          unitFigure.addFigureChangeListener(this);
          unitFigureMap.put(unit, unitFigure);

          // also insert in delegate list as it is
          // this list that is iterated by the
          // graphics rendering algorithms
          delegate.add(unitFigure);
        }
      }
    }
  }


  protected void defineCityMap() {
    // ensure no units of the old list are accidental in
    // the selection!
    clearSelection();

    // remove all city figures in this drawing
    removeAllCityFigures();

    // iterate world, and create a city figure for each city in the game world, as well as
    // create an association between the city and the cityFigure in 'cityFigureMap'.
    Position p;
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        p = new Position(r,c);
        City city = game.getCityAt(p);
        if ( city != null ) {

          // convert the unit's Position to (x,y) coordinates
          Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()), GfxConstants.getYFromRow(p.getRow()) );
          CityFigure cityFigure = new CityFigure(city, point);
          cityFigure.addFigureChangeListener(this);
          cityFigureMap.put(city, cityFigure);

          // also insert in delegate list as it is this list that is iterated by the graphics rendering algorithms
          delegate.add(cityFigure);
        }
      }
    }
  }


  /** remove all unit figures in this
   * drawing, and reset the map (unit->unitfigure).
   * It is important to actually remove the figures
   * as it forces a graphical redraw of the screen
   * where the unit figure was.
   */
  protected void removeAllUnitFigures() {
    for (Unit u : unitFigureMap.keySet()) {
      UnitFigure uf = unitFigureMap.get(u);
      delegate.remove(uf);
    }
    unitFigureMap.clear();
  }

  protected void removeAllCityFigures() {
    for (City c : cityFigureMap.keySet()) {
      CityFigure cf = cityFigureMap.get(c);
      delegate.remove(cf);
    }
    cityFigureMap.clear();
  }

  protected ImageFigure turnShieldIcon;
  protected ImageFigure unitShieldIcon;
  protected ImageFigure cityShieldIcon;
  protected ImageFigure workforceFocusIcon;
  protected ImageFigure cityProductionIcon;
  protected ImageFigure refreshButtonIcon;
  protected TextFigure ageText;
  protected void defineIcons() {
    // TODO: Further development to include rest of figures needed - DONE
    turnShieldIcon = new ImageFigure( GfxConstants.RED_SHIELD, new Point( GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y ));
    unitShieldIcon = new ImageFigure(GfxConstants.NOTHING, new Point(GfxConstants.UNIT_SHIELD_X, GfxConstants.UNIT_SHIELD_Y));
    cityShieldIcon = new ImageFigure(GfxConstants.NOTHING, new Point(GfxConstants.CITY_SHIELD_X, GfxConstants.CITY_SHIELD_Y));
    workforceFocusIcon = new ImageFigure(GfxConstants.NOTHING, new Point(GfxConstants.WORKFORCEFOCUS_X, GfxConstants.WORKFORCEFOCUS_Y));
    cityProductionIcon = new ImageFigure(GfxConstants.NOTHING, new Point(GfxConstants.CITY_PRODUCTION_X, GfxConstants.CITY_PRODUCTION_Y));
    refreshButtonIcon = new ImageFigure(GfxConstants.REFRESH_BUTTON, new Point(GfxConstants.REFRESH_BUTTON_X, GfxConstants.REFRESH_BUTTON_Y));
    ageText = new TextFigure(((Integer)GameVariables.age).toString(), new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y));

    // insert in delegate figure list to ensure graphical rendering.
    delegate.add(turnShieldIcon);
    delegate.add(unitShieldIcon);
    delegate.add(cityShieldIcon);
    delegate.add(workforceFocusIcon);
    delegate.add(cityProductionIcon);
    delegate.add(refreshButtonIcon);
    delegate.add(ageText);
  }
 
  // === Observer Methods ===

  public void worldChangedAt(Position pos) {
    // TODO: Remove system.out debugging output
    System.out.println( "CivDrawing: world changes at "+pos);
    // this is a really brute-force algorithm: destroy
    // all known units and build up the entire set again
    defineUnitMap();

    // TODO: Cities may change on position as well - DONE
    defineCityMap();
  }

  public void turnEnds(Player nextPlayer) {
    int age = GameVariables.age;
    // TODO: Remove system.out debugging output
    System.out.println("CivDrawing: turnEnds for " + nextPlayer + " at "+age );
    String playerName = playerHashMap.get(nextPlayer);
    turnShieldIcon.set( playerName+"shield", new Point( GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y ));

    // TODO: Age output pending - DONE
    ageText.setText(((Integer) GameVariables.age).toString());

  }

  public void tileFocusChangedAt(Position pos) {
    // TODO: Implementation pending
    // If you select a unit, then the shields should change to the appropriate colours
    if(game.getUnitAt(pos) != null){
      String ownerName = playerHashMap.get(game.getUnitAt(pos).getOwner());
      unitShieldIcon.set(ownerName + "shield", new Point( GfxConstants.UNIT_SHIELD_X, GfxConstants.UNIT_SHIELD_Y ));
    }
    else {
      unitShieldIcon.set(GfxConstants.NOTHING, new Point( GfxConstants.UNIT_SHIELD_X, GfxConstants.UNIT_SHIELD_Y ));

    }

    // If you select a city
    if(game.getCityAt(pos) != null){
      // Change shield
      String ownerName = playerHashMap.get(game.getCityAt(pos).getOwner());
      cityShieldIcon.set(ownerName + "shield", new Point( GfxConstants.CITY_SHIELD_X, GfxConstants.CITY_SHIELD_Y ));

      // Change production
      String production = game.getCityAt(pos).getProduction();
      cityProductionIcon.set(production, new Point(GfxConstants.CITY_PRODUCTION_X, GfxConstants.CITY_PRODUCTION_Y));

      // Change focus
      String focus = game.getCityAt(pos).getWorkforceFocus();
      workforceFocusIcon.set(focus, new Point(GfxConstants.WORKFORCEFOCUS_X, GfxConstants.WORKFORCEFOCUS_Y));
    }
    else {
      cityShieldIcon.set(GfxConstants.NOTHING, new Point( GfxConstants.CITY_SHIELD_X, GfxConstants.CITY_SHIELD_Y ));
      cityProductionIcon.set(GfxConstants.NOTHING, new Point(GfxConstants.CITY_PRODUCTION_X, GfxConstants.CITY_PRODUCTION_Y));
      workforceFocusIcon.set(GfxConstants.NOTHING, new Point(GfxConstants.WORKFORCEFOCUS_X, GfxConstants.WORKFORCEFOCUS_Y));
    }
  }

  @Override
  public void requestUpdate() {
    // A request has been issued to repaint everything. We simply rebuild the entire Drawing.
    defineUnitMap();
    defineIcons();
    defineCityMap();
    // TODO: Cities pending - DONE
  }

  @Override
  public void addToSelection(Figure arg0) {
    delegate.addToSelection(arg0);
  }

  @Override
  public void clearSelection() {
    delegate.clearSelection();
  }

  @Override
  public void removeFromSelection(Figure arg0) {
    delegate.removeFromSelection(arg0);
  }

  @Override
  public List<Figure> selection() {
    return delegate.selection();
  }

  @Override
  public void toggleSelection(Figure arg0) {
    delegate.toggleSelection(arg0);
  }

  @Override
  public void figureChanged(FigureChangeEvent arg0) {
    delegate.figureChanged(arg0);
  }

  @Override
  public void figureInvalidated(FigureChangeEvent arg0) {
    delegate.figureInvalidated(arg0);
  }

  @Override
  public void figureRemoved(FigureChangeEvent arg0) {
    delegate.figureRemoved(arg0);
  }

  @Override
  public void figureRequestRemove(FigureChangeEvent arg0) {
    delegate.figureRequestRemove(arg0);
  }

  @Override
  public void figureRequestUpdate(FigureChangeEvent arg0) {
    delegate.figureRequestUpdate(arg0);
  }

  @Override
  public void addDrawingChangeListener(DrawingChangeListener arg0) {
    delegate.addDrawingChangeListener(arg0);   
  }

  @Override
  public void removeDrawingChangeListener(DrawingChangeListener arg0) {
    delegate.removeDrawingChangeListener(arg0);
  }

  @Override
  public Figure findFigure(int arg0, int arg1) {
    return delegate.findFigure(arg0, arg1);
  }

  @Override
  public Iterator<Figure> iterator() {
    return delegate.iterator();
  }

  @Override
  public void lock() {
    delegate.lock();
  }

  @Override
  public Figure remove(Figure arg0) {
    return delegate.remove(arg0);
  }

  @Override
  public void unlock() {
    delegate.unlock();
  }
}
