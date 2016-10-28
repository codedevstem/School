import java.util.Scanner;

/**
 * Created by Kristian Os on 27.10.2016.
 */

public class LabyrintVandring {
    static int labyrintBredde = 4, labyrintHoeyde = 5;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int playerposHeight = 0, playerposWidth = 0;
        char[][] labyrint = {
                { '*','*','*','*'},
                { '*',' ',' ','*'},
                { '*',' ','*','*'},
                { '*','s','*','*'},
                { '*','*','*','*'},
        };
        String motion = "";
        while (!motion.equals("exit")) {
            System.out.println("Labyrinth");
            for (int i = 0; i < labyrintHoeyde; i++) {
                for (int j = 0; j < labyrintBredde; j++) {
                    System.out.print(labyrint[i][j]);
                    if (labyrint[i][j] == 's') {
                        playerposHeight = i;
                        playerposWidth = j;
                    }
                }
                System.out.println();
            }
            System.out.println("Where do you want to move? Write 'north', 'south', 'west' or 'east' to move in the respective direction, or 'exit' to exit.");
            motion = input.nextLine().toLowerCase();
            if (kanGaaTil(labyrint, motion, playerposHeight, playerposWidth)){
                if (motion.equals("north")){
                    labyrint[playerposHeight-1][playerposWidth] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                } else if (motion.equals("south")){
                    labyrint[playerposHeight+1][playerposWidth] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                }else if (motion.equals("west")){
                    labyrint[playerposHeight][playerposWidth-1] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                } else if (motion.equals("east")){
                    labyrint[playerposHeight][playerposWidth+1] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                }
            }
        }
    }

    private static boolean kanGaaTil(char[][] labyrint, String motion, int playerposH, int playerposW) {
        if (motion.equals("north"))
            if (labyrint[playerposH-1][playerposW] != '*') return true;
        if (motion.equals("south"))
            if (labyrint[playerposH+1][playerposW] != '*') return true;
        if (motion.equals("west"))
            if (labyrint[playerposH][playerposW-1] != '*') return true;
        if (motion.equals("east"))
            if (labyrint[playerposH][playerposW+1] != '*') return true;
        System.out.println("The way is blocked!");
        return false;
    }
}
