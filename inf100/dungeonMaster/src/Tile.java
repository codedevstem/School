/**
 * Created by Kristian Os on 03.11.2016.
 */
public class Tile {
    private TileTypes type;

    public Tile(TileTypes type) {
        this.type = type;
    }

    public TileTypes getType() {
        return type;
    }

    public void setType(TileTypes type) {
        this.type = type;
    }
}
