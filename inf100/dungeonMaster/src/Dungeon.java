/**
 * Created by Kristian Os on 03.11.2016.
 */
public class Dungeon {
    Room room;
    Player player;

    public Dungeon() {
        room = new Room(15, 50);
        player = new Player("Bob");
    }

    public Room getRoom() {
        return room;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayerPos() {
        for (int y = 0; y < getRoom().getHeight(); y++) {
            for (int x = 0; x < getRoom().getWidth(); x++) {
                if (getRoom().getTileType(x,y).equals(TileTypes.OPEN)){
                    getRoom().setTileType(x,y, TileTypes.PLAYER);
                    player.setPosX(x);
                    player.setPosY(y);
                    return;
                }
            }
        }
    }
    public void movePlayer(){
        int currentPosX = player.getPosX();
        int currentPosY = player.getPosY();
        IO.out("MOVE from " + currentPosX + currentPosY);

        switch (getPlayer().getAction()){
            case UP : {
                if (!room.getTileType(currentPosX, currentPosY+1).equals(TileTypes.WALL)){
                    player.setPosY(player.getPosY()+1);
                    room.setTileType(currentPosX, currentPosY, TileTypes.OPEN);
                    room.setTileType(currentPosX, currentPosY+1, TileTypes.PLAYER);
                    IO.out("UP!");
                }
                break;
            }
            case DOWN :{
                if (!room.getTileType(currentPosX, currentPosY-1).equals(TileTypes.WALL)){
                    player.setPosY(player.getPosY()-1);
                    room.setTileType(currentPosX, currentPosY, TileTypes.OPEN);
                    room.setTileType(currentPosX, currentPosY-1, TileTypes.PLAYER);
                }

                break;
            }
            case RIGHT : {
                if (!room.getTileType(currentPosX+1, currentPosY).equals(TileTypes.WALL)){
                    player.setPosX(player.getPosX()+1);
                    room.setTileType(currentPosX, currentPosY, TileTypes.OPEN);
                    room.setTileType(currentPosX+1, currentPosY, TileTypes.PLAYER);
                }
                break;
            }
            case LEFT :{
                if (!room.getTileType(currentPosX-1, currentPosY).equals(TileTypes.WALL)){
                    player.setPosX(player.getPosX()-1);
                    room.setTileType(currentPosX, currentPosY, TileTypes.OPEN);
                    room.setTileType(currentPosX-1, currentPosY, TileTypes.PLAYER);
                }
                break;
            }
        }
    }
    public void printDungeon() {
        String outString = "";
        for (int i = getRoom().getHeight()-1; i >= 0; i--) {
            for (int j = 0; j < getRoom().getWidth(); j++) {
                if (getRoom().getTileType(j,i).equals(TileTypes.WALL)){
                    outString += "X ";
                }else if (getRoom().getTileType(j,i).equals(TileTypes.OPEN)){
                    outString += "  ";
                }else if (getRoom().getTileType(j,i).equals(TileTypes.PLAYER)){
                    outString += "O ";
                }
            }
            outString += "\n";
        }
        IO.out(outString);
    }
}
