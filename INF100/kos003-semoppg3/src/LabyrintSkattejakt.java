import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static sun.audio.AudioPlayer.player;

/**
 * Created by Kristian Os on 27.10.2016.
 */
public class LabyrintSkattejakt {
    // set static variables. Would use multiple classes here so this would not be nessesary.
    static int labyrintBredde = 0, labyrintHoeyde = 0, gold = 0, numberOfGold = 0,
            playerposHeight, playerposWidth;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] labyrint = null;
        String motion = "";
        System.out.println("Labyrinth-File: ");
        String file = input.nextLine();
        labyrint = lasLabyrintFraFil(file);
        for (int i = 0; i < labyrintHoeyde; i++) {
            for (int j = 0; j < labyrintBredde; j++) {
                if (labyrint[i][j] == 's') {
                    playerposHeight = i;
                    playerposWidth = j;
                }
                if (labyrint[i][j] == 'g') {
                    numberOfGold++;
                }
            }
        }
        System.out.println(labyrintBredde + "\n" + labyrintHoeyde);
        // Main loop
        while (!motion.equals("exit")) {
            // Winningcondition
            if (gold == numberOfGold) {
                break;
            }
            System.out.println("Player-gold: " + gold);
            System.out.println("Labyrinth");
            //Print out labyrinth
            for (int i = 0; i < labyrintHoeyde; i++) {
                for (int j = 0; j < labyrintBredde; j++) {
                    System.out.print(labyrint[i][j]);
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

        if (gold == numberOfGold){
            System.out.println("You got all the gold in the labyrinth");
            System.out.println("Player gold : " + gold);
        }
    }
    /**
     * Moving the player to the selected field after first beeing checked ok.
     * @param labyrint - The laberynth that is observable.
     * @param nextPosHeight - The next position in height.
     * @param nextPosWidth - The next position in width
     */
    private static void movePlayer(char[][] labyrint, int nextPosHeight, int nextPosWidth) {
        // Check if next pos is gold.
        if (labyrint[nextPosHeight][nextPosWidth] == 'g') {
            gold++;
            System.out.println("You got one gold!");
        }
        // Set next pos to 's'
        labyrint[nextPosHeight][nextPosWidth] = 's';
        // Set prev pos to ' '
        labyrint[playerposHeight][playerposWidth] = ' ';
        // set the new playerposition.
        playerposHeight = nextPosHeight;
        playerposWidth = nextPosWidth;
    }
    /**
     * Check if the position that the player wants to move to is possible to move to.
     * @param labyrint - The laberynth that is observable.
     * @param motion - The motion that the player has initiated.
     * @param nextPosHeight - The next position in height.
     * @param nextPosWidth - The next position in width
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
     * Reads the file and returns a laberynth based on the contents of the file.
     * @param file to be read
     * @return a laberynth represented in a 2d array.
     */
    private static char[][] lasLabyrintFraFil(String file) {
        // temporary laberynth
        char[][] tempLabyrinth = null;
        // Try, catch for reading the file.
        try {
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new FileReader("src/" + file + ".txt"));
            // Try catch for the laberynth width and height.
            try {
                labyrintBredde = Integer.parseInt(bufferedReader.readLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (labyrintBredde < 1){
                System.err.println("Width was too low.");
                System.exit(-1);
            }

            try {
                labyrintHoeyde = Integer.parseInt(bufferedReader.readLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (labyrintBredde < 1){
                System.err.println("Width was too low.");
                System.exit(-1);
            }
            String line;
            // Initate the laberynth with width and height.
            tempLabyrinth = new char[labyrintHoeyde][labyrintBredde];
            int labLine = 0;
            // Read line for line.
            while ((line = bufferedReader.readLine()) != null){
                // Get each charater.
                for (int i = 0; i < line.length(); i++)
                    // Insert each charater into the laberynth
                    tempLabyrinth[labLine][i] = line.charAt(i);
                labLine++;
                //fail test for less chars than the specifications said there should be.
                if (line.length() != labyrintBredde && labLine-1 != labyrintHoeyde ){
                    System.err.println("Missing width : was " + line.length() + " Should be : " + labyrintBredde);
                    System.exit(-1);
                }
            }
            //fail test for less lines than the specifications said there should be.
            if (labLine != labyrintHoeyde){
                System.err.println("Missing height : was " + labLine + " Should be : " + labyrintHoeyde);
                System.exit(-1);
            }
        } catch (IOException e) {
            System.err.println("Failed to read the file");
            e.printStackTrace();
        }
        return tempLabyrinth;
    }
}

