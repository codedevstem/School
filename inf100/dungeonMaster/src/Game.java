/**
 * Created by Kristian Os on 03.11.2016.
 */
public class Game {
    boolean finished;
    Dungeon dungeon;

    public Game() {
        this.finished = false;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void runGame() {
        dungeon = new Dungeon();
        Player character = dungeon.getPlayer();
        IO.out("This is your dungeon!\n " +
                "What is your name: ");
        character.setName(IO.in());
        dungeon.setPlayerPos();
        while (dungeon.player.isAlive()){
            dungeon.printDungeon();
            IO.out("Move around![up,down,left,right]");
            character.setAction(IO.in().toLowerCase());
            dungeon.movePlayer();
            character.setAction("Null");


        }
    }
}
