package hotciv.standard ;
import hotciv.framework.*;

public class TileImpl implements Tile{
    Position pos;
    String type;
    CityImpl city;
    // Missing unit implementation

    public TileImpl(){};

    public TileImpl(Position position, String type, CityImpl city){
        this.pos = position;
        this.type = type;
        this.city = city;
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
}
