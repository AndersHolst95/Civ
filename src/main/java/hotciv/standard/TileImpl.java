package hotciv.standard ;
import hotciv.framework.*;

public class TileImpl implements Tile{
    Position pos;
    String type;
    CityImpl city;
    UnitImpl unit;

    public TileImpl(){};

    public TileImpl(Position position, String type, CityImpl city, UnitImpl unit){
        this.pos = position;
        this.type = type;
        this.city = city;
        this.unit = unit;

    }

    @Override
    public String getTypeString() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setCity(CityImpl city) {
        this.city = city;
    }

    public void setUnit(UnitImpl unit) {
        this.unit = unit;
    }

    public UnitImpl getUnit() {
        return unit;
    }

    public CityImpl getCity() {
        return city;
    }
}
