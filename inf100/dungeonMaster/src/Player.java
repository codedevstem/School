/**
 * Created by Kristian Os on 03.11.2016.
 */
public class Player {
    String name;
    boolean alive;
    int coin;
    Action action;
    int posX;
    int posY;

    public Player(String name) {
        this.name = name;
        alive = true;
        coin = 0;
        action = null;
        posX = 0;
        posY = 0;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(String action) {
        if (action.equals("up")){
            this.action = Action.UP;
        }
        else if (action.equals("down")){
            this.action = Action.DOWN;
        }
        else if (action.equals("left")){
            this.action = Action.LEFT;
        }
        else if (action.equals("right")){
            this.action = Action.RIGHT;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
