package hotciv.standard ;
import hotciv.framework.*;
import java.util.UUID;

public class TileImpl implements Tile{
    private Position pos;
    private String type;
    private CityImpl city;
    private UnitImpl unit;
    private String id = UUID.randomUUID().toString();

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

    public String getId(){
        return id;
    }
}
