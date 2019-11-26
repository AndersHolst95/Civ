package hotciv.view;

import hotciv.framework.*;

import java.awt.*;
import java.util.*;
import java.util.List;

import hotciv.standard.TileImpl;
import minidraw.framework.*;
import minidraw.standard.*;

public class CivDrawing implements Drawing, GameObserver {

    protected Drawing delegate;
    /** store all moveable figures visible in this drawing = units */
    protected Map<Unit, UnitFigure> unitFigureMap;
    protected Map<City, CityFigure> cityFigureMap;
    protected List<TextFigure> textFigures;
    protected List<ImageFigure> imageFigures;

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
        this.textFigures = new ArrayList<>();
        this.imageFigures = new ArrayList<>();

        // register this unit drawing as listener to any game state
        // changes...
        game.addObserver(this);
        // ... and build up the set of figures associated with
        // units in the game
        World.setTileMap(game.getTileMap());
        defineCityMap(World.getMap());
        defineUnitMap(World.getMap());
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
    protected void defineUnitMap(TileImpl[][] map) {
        // ensure no units of the old list are accidental in
        // the selection!
        clearSelection();

        // remove all unit figures in this drawing
        removeAllUnitFigures();

        // iterate world, and create a unit figure for
        // each unit in the game world, as well as
        // create an association between the unit and
        // the unitFigure in 'unitFigureMap'.
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                Unit unit = map[r][c].getUnit();
                if ( unit != null ) {
                    String type = unit.getTypeString();
                    // convert the unit's Position to (x,y) coordinates
                    Point point = new Point( GfxConstants.getXFromColumn(c),
                            GfxConstants.getYFromRow(r) );
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

    protected void defineCityMap(TileImpl[][] map) {
        // ensure no units of the old list are accidental in
        // the selection!
        clearSelection();

        // remove all city figures in this drawing
        removeAllCityFigures();

        // iterate world, and create a city figure for each city in the game world, as well as
        // create an association between the city and the cityFigure in 'cityFigureMap'.
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                City city = map[r][c].getCity();
                if ( city != null ) {

                    // convert the unit's Position to (x,y) coordinates
                    Point point = new Point( GfxConstants.getXFromColumn(c), GfxConstants.getYFromRow(r) );
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

    protected void removeAllTextFigures() {
        for (TextFigure tf : textFigures) {
            delegate.remove(tf);
        }
        textFigures.clear();
    }

    protected void removeAlImageFigures() {
        for (ImageFigure imf : imageFigures) {
            delegate.remove(imf);
        }
        imageFigures.clear();
    }


    protected ImageFigure turnShieldIcon;
    protected ImageFigure unitShieldIcon;
    protected ImageFigure cityShieldIcon;
    protected ImageFigure workforceFocusIcon;
    protected ImageFigure cityProductionIcon;
    protected ImageFigure refreshButtonIcon;
    protected TextFigure moveCountText;
    protected TextFigure ageText;
    protected void defineIcons() {
        removeAlImageFigures();
        removeAllTextFigures();
        Integer age = game.getAge();
        turnShieldIcon = new ImageFigure( GfxConstants.RED_SHIELD, new Point( GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y ));
        unitShieldIcon = new ImageFigure(GfxConstants.NOTHING, new Point(GfxConstants.UNIT_SHIELD_X, GfxConstants.UNIT_SHIELD_Y));
        cityShieldIcon = new ImageFigure(GfxConstants.NOTHING, new Point(GfxConstants.CITY_SHIELD_X, GfxConstants.CITY_SHIELD_Y));
        workforceFocusIcon = new ImageFigure(GfxConstants.NOTHING, new Point(GfxConstants.WORKFORCEFOCUS_X, GfxConstants.WORKFORCEFOCUS_Y));
        cityProductionIcon = new ImageFigure(GfxConstants.NOTHING, new Point(GfxConstants.CITY_PRODUCTION_X, GfxConstants.CITY_PRODUCTION_Y));
        refreshButtonIcon = new ImageFigure(GfxConstants.REFRESH_BUTTON, new Point(GfxConstants.REFRESH_BUTTON_X, GfxConstants.REFRESH_BUTTON_Y));
        ageText = new TextFigure(age.toString(), new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y));
        moveCountText = new TextFigure("", new Point(GfxConstants.UNIT_COUNT_X, GfxConstants.UNIT_COUNT_Y));

        imageFigures.add(turnShieldIcon);
        imageFigures.add(cityShieldIcon);
        imageFigures.add(workforceFocusIcon);
        imageFigures.add(cityProductionIcon);
        imageFigures.add(unitShieldIcon);
        imageFigures.add(refreshButtonIcon);
        textFigures.add(ageText);
        textFigures.add(moveCountText);

        // insert in delegate figure list to ensure graphical rendering.
        delegate.add(turnShieldIcon);
        delegate.add(cityShieldIcon);
        delegate.add(workforceFocusIcon);
        delegate.add(cityProductionIcon);
        delegate.add(unitShieldIcon);
        delegate.add(refreshButtonIcon);
        delegate.add(ageText);
        delegate.add(moveCountText);
    }

    protected void resetIcons() {
        unitShieldIcon.set(GfxConstants.NOTHING, new Point(GfxConstants.UNIT_SHIELD_X, GfxConstants.UNIT_SHIELD_Y));
        cityShieldIcon.set(GfxConstants.NOTHING, new Point(GfxConstants.CITY_SHIELD_X, GfxConstants.CITY_SHIELD_Y));
        workforceFocusIcon.set(GfxConstants.NOTHING, new Point(GfxConstants.WORKFORCEFOCUS_X, GfxConstants.WORKFORCEFOCUS_Y));
        cityProductionIcon.set(GfxConstants.NOTHING, new Point(GfxConstants.CITY_PRODUCTION_X, GfxConstants.CITY_PRODUCTION_Y));
        updateIcons();
    }

    protected void updateIcons(){
        clearSelection();
        removeAlImageFigures();
        removeAllTextFigures();

        delegate.add(turnShieldIcon);
        delegate.add(cityShieldIcon);
        delegate.add(workforceFocusIcon);
        delegate.add(cityProductionIcon);
        delegate.add(unitShieldIcon);
        delegate.add(refreshButtonIcon);
        delegate.add(ageText);
        delegate.add(moveCountText);
    }
    // === Observer Methods ===

    public void worldChangedAt(Position pos) {
        World.setTileMap(game.getTileMap());
        defineCityMap(World.getMap());
        defineUnitMap(World.getMap());
        updateIcons();
    }

    public void turnEnds(Player nextPlayer) {
        resetIcons();
        String playerName = playerHashMap.get(nextPlayer);
        turnShieldIcon.set( playerName+"shield", new Point( GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y ));
        ageText.setText(((Integer) game.getAge()).toString());
        World.setTileMap(game.getTileMap());
    }

    public void tileFocusChangedAt(Position pos) {
        // TODO: Implementation pending
        // If you select a unit, then the shields should change to the appropriate colours
        if(game.getUnitAt(pos) != null){
            String ownerName = playerHashMap.get(game.getUnitAt(pos).getOwner());
            unitShieldIcon.set(ownerName + "shield", new Point( GfxConstants.UNIT_SHIELD_X, GfxConstants.UNIT_SHIELD_Y ));
            moveCountText.setText(((Integer) game.getUnitAt(pos).getMoveCount()).toString());
        }
        else {
            unitShieldIcon.set(GfxConstants.NOTHING, new Point( GfxConstants.UNIT_SHIELD_X, GfxConstants.UNIT_SHIELD_Y ));
            moveCountText.setText("");
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
        World.setTileMap(game.getTileMap());
        updateIcons();
        defineCityMap(World.getMap());
        defineUnitMap(World.getMap());
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
