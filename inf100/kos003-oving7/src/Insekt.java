/**
 * Created by Kristian Os on 18.11.2016.
 */
public class Insekt {
    int xKord, yKord, retning;

    public Insekt(int xKord, int yKord) {
        this.xKord = xKord;
        this.yKord = yKord;
        this.retning = 0;
    }

    public void snuMotVenstre(){
        if (retning == 0){
            retning = 3;
        }else{
            retning--;
        }
    }
    public void snuMotHoeyre(){
        if (retning == 3) retning = 0;
        else retning++;
    }
    public void bevegFremover(){
        switch (retning){
            case 0: xKord++;break;
            case 1: yKord++;break;
            case 2: xKord--;break;
            case 3: yKord--;break;
        }
    }

    public int getxKord() {
        return xKord;
    }

    public int getyKord() {
        return yKord;
    }

    @Override
    public String toString() {
        return "Insect[" +
                "xPosition=" + xKord +
                ",yPosition=" + yKord +
                ",direction=" + retning +
                ']';
    }
}
