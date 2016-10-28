import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static sun.audio.AudioPlayer.player;

/**
 * Created by Kristian Os on 27.10.2016.
 */
public class LabyrintSkattejakt {
    static int labyrintBredde = 0, labyrintHoeyde = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] labyrint = null;
        int numberOfGold = 0,playerposHeight = 0, playerposWidth = 0,gold = 0;
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
        while (!motion.equals("exit")) {
            if (gold == numberOfGold) {
                break;
            }
            System.out.println("Player-gold: " + gold);
            System.out.println("Labyrinth");
            for (int i = 0; i < labyrintHoeyde; i++) {
                for (int j = 0; j < labyrintBredde; j++) {
                    System.out.print(labyrint[i][j]);
                }
                System.out.println();
            }
            System.out.println("Where do you want to move? Write 'north', 'south', 'west' or 'east' " +
                    "to move in the respective direction, or 'exit' to exit.");
            motion = input.nextLine().toLowerCase();
            if (kanGaaTil(labyrint, motion, playerposHeight, playerposWidth)){
                if (motion.equals("north")){
                    if (labyrint[playerposHeight-1][playerposWidth] == 'g') {
                        gold++;
                        System.out.println("You got one gold!");
                    }
                    labyrint[playerposHeight-1][playerposWidth] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                    playerposHeight--;
                } else if (motion.equals("south")){
                    if (labyrint[playerposHeight+1][playerposWidth] == 'g') {
                        gold++;
                        System.out.println("You got one gold!");
                    }
                    labyrint[playerposHeight+1][playerposWidth] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                    playerposHeight++;
                }else if (motion.equals("west")){
                    if (labyrint[playerposHeight][playerposWidth-1] == 'g') {
                        gold++;
                        System.out.println("You got one gold!");
                    }
                    labyrint[playerposHeight][playerposWidth-1] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                    playerposWidth--;
                } else if (motion.equals("east")){
                    if (labyrint[playerposHeight][playerposWidth+1] == 'g'){
                        gold++;
                        System.out.println("You got one gold!");
                    }
                    labyrint[playerposHeight][playerposWidth+1] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                    playerposWidth++;
                }
            }
        }
        if (gold == numberOfGold){
            System.out.println("You got all the gold in the labyrinth");
            System.out.println("Player gold : " + gold);
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

    private static char[][] lasLabyrintFraFil(String file) {
        char[][] tempLabyrinth = null;
        try {
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new FileReader("src/" + file + ".txt"));
            labyrintBredde = Integer.parseInt(bufferedReader.readLine());
            if (labyrintBredde < 1){
                System.err.println("Width was too low.");
                System.exit(-1);
            }

            labyrintHoeyde = Integer.parseInt(bufferedReader.readLine());
            if (labyrintBredde < 1){
                System.err.println("Width was too low.");
                System.exit(-1);
            }
            String line;
            tempLabyrinth = new char[labyrintHoeyde][labyrintBredde];
            int labLine = 0;
            while ((line = bufferedReader.readLine()) != null){
                for (int i = 0; i < line.length(); i++)
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

