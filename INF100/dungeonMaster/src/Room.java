/**
 * Created by Kristian Os on 03.11.2016.
 */
public class Room {
    private int height;
    private int width;
    private Tile[][] tiles;


    private Tile[][] generateTiles() {
        Tile[][] tempTiles = new Tile[getWidth()][getHeight()];
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                if (y == 0 || y == width-1 || x == 0 || x == height-1)
                    tempTiles[y][x] = new Tile(TileTypes.WALL);
                else{
                    int chance = (int) Math.floor(Math.random()*4);
                    if (chance == 1){
                        tempTiles[y][x] = new Tile(TileTypes.WALL);
                    }else
                    tempTiles[y][x] = new Tile(TileTypes.OPEN);
                }
            }
        }
        return tempTiles;
    }

    public Room(int height, int width) {
        this.height = height;
        this.width = width;
        tiles = generateTiles();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
    public TileTypes getTileType(int x, int y) {
        return tiles[x][y].getType();
    }
    public void setTileType(int x, int y, TileTypes type) {
        tiles[x][y].setType(type);
    }



}
