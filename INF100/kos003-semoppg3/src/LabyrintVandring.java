import java.util.Scanner;

/**
 * Created by Kristian Os on 27.10.2016.
 */

public class LabyrintVandring {
    // set static variables. Would use multiple classes here so this would not be nessesary.

    static int labyrintBredde = 4, labyrintHoeyde = 5, playerposHeight, playerposWidth;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Predefining the laberynth in a 2d array with char
        char[][] labyrint = {
                { '*','*','*','*'},
                { '*',' ',' ','*'},
                { '*',' ','*','*'},
                { '*','s','*','*'},
                { '*','*','*','*'},
        };
        String motion = "";
        // Main game loop
        while (!motion.equals("exit")) {
            System.out.println("Labyrinth");
            // Print out map.
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
            System.out.println("Where do you want to move? Write 'north', 'south', 'west' or 'east' " +
                    "to move in the respective direction, or 'exit' to exit.");
            boolean fail = false;
            // get input till it is correct.
            do {
                motion = input.nextLine().toLowerCase();
                if (!(motion.equals("north") || motion.equals("south") || motion.equals("west")
                        || motion.equals("east") || motion.equals("exit"))) {
                    System.out.println("Error: type a new input");
                    fail = true;
                } else {
                    fail = false;
                }
            } while(fail);
            // Decide if the direction can be moved in.
            kanGaaTil(labyrint, motion, playerposHeight, playerposWidth);
        }
    }


    /**
     * Check if the position that the player wants to move to is possible to move to.
     * @param labyrint - The laberynth that is observable.
     * @param motion - The motion that the player has initiated.
     * @param playerposH - The next position in height.
     * @param playerposW- The next position in width
     */
    private static void kanGaaTil(char[][] labyrint, String motion, int playerposH, int playerposW) {
        boolean blocked = true;
        // if the motion is north
        if (motion.equals("north"))
            // Check if the next position is a wall
            if (labyrint[playerposH-1][playerposW] != '*') {
                // Move the player to the correct position.
                movePlayer(labyrint, playerposH-1, playerposW);
                blocked = false;
            }
        // if the motion is south
        if (motion.equals("south"))
            // Check if the next position is a wall
            if (labyrint[playerposH+1][playerposW] != '*') {
                // Move the player to the correct position.
                movePlayer(labyrint, playerposH+1, playerposW);
                blocked = false;

            }
        // if the motion is west
        if (motion.equals("west"))
            // Check if the next position is a wall
            if (labyrint[playerposH][playerposW-1] != '*'){
                // Move the player to the correct position.
                movePlayer(labyrint, playerposH, playerposW-1);
                blocked = false;

            }
        // if the motion is east
        if (motion.equals("east"))
            // Check if the next position is a wall
            if (labyrint[playerposH][playerposW+1] != '*'){
                // Move the player to the correct position.
                movePlayer(labyrint, playerposH, playerposW+1);
                blocked = false;
            }
        // If the way is a wall.
        if (blocked) System.out.println("The way is blocked!");
    }

    /**
     * Moving the player to the selected field after first beeing checked ok.
     * @param labyrint - The laberynth that is observable.
     * @param nextPosHeight - The next position in height.
     * @param nextPosWidth - The next position in width
     */
    private static void movePlayer(char[][] labyrint, int nextPosHeight, int nextPosWidth) {
        labyrint[nextPosHeight][nextPosWidth] = 's';
        labyrint[playerposHeight][playerposWidth] = ' ';
        playerposHeight = nextPosHeight;
        playerposWidth = nextPosWidth;
    }
}
