import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kristian Os on 27.10.2016.
 */
public class LabyrintMonstre {
    static int labyrintBredde = 0, labyrintHoeyde = 0, gold = 0, numberOfGold = 0;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[][] labyrint = null;
        int playerposHeight = 0, playerposWidth = 0;
        String motion = "";
        System.out.println("Labyrinth-File: ");
        String file = "testLabyrint2";
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
            //
            for (int i = 0; i < labyrintHoeyde; i++) {
                for (int j = 0; j < labyrintBredde; j++) {
                    System.out.print(labyrint[i][j]);
                }
                System.out.println();
            }
            System.out.println("Where do you want to move? Write 'north', 'south', 'west' or 'east' " +
                    "to move in the respective direction, or 'exit' to exit.");
            boolean fail = false;
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
            if (kanGaaTil(labyrint, motion, playerposHeight, playerposWidth)){
                if (motion.equals("north")){
                    if (labyrint[playerposHeight-1][playerposWidth] == 'g') {
                        gold++;
                        System.out.println("You got one gold!");
                    } else if (labyrint[playerposHeight-1][playerposWidth] == 'm'){
                        monserKamp(false);
                    }
                    labyrint[playerposHeight-1][playerposWidth] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                    playerposHeight--;
                } else if (motion.equals("south")){
                    if (labyrint[playerposHeight+1][playerposWidth] == 'g') {
                        gold++;
                        System.out.println("You got one gold!");
                    } else if (labyrint[playerposHeight+1][playerposWidth] == 'm'){
                        monserKamp(false);
                    }
                    labyrint[playerposHeight+1][playerposWidth] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                    playerposHeight++;
                }else if (motion.equals("west")){
                    if (labyrint[playerposHeight][playerposWidth-1] == 'g') {
                        gold++;
                        System.out.println("You got one gold!");
                    }else if (labyrint[playerposHeight][playerposWidth-1] == 'm'){
                        monserKamp(false);
                    }
                    labyrint[playerposHeight][playerposWidth-1] = 's';
                    labyrint[playerposHeight][playerposWidth] = ' ';
                    playerposWidth--;
                } else if (motion.equals("east")){
                    if (labyrint[playerposHeight][playerposWidth+1] == 'g'){
                        gold++;
                        System.out.println("You got one gold!");
                    }else if (labyrint[playerposHeight][playerposWidth+1] == 'm'){
                        monserKamp(false);
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

    private static void monserKamp( boolean reroll) {
        if (gold == -1) {
            System.out.println("You are out of gold!");
            System.out.println("The monster ate you!");
            System.exit(-1);
        }
        Random dice = new Random();
        int yourThrow = dice.nextInt(6)+1;
        int monsterThrow = dice.nextInt(6)+1;
        if (!reroll)
            System.out.println("A monster blocks the road! He challenges you to roll a dice and get the highest number\n"
                + "You and the monster plays. \n" +
                "Your throw: " + yourThrow + "\n" +
                "The monster throws: " + monsterThrow);
        else
            System.out.println("Your new throw:" + yourThrow + "\n" +
                    "The monsters new throws: " + monsterThrow);
        if (yourThrow > monsterThrow){
            System.out.println("You threw higher than the monster. He lets you pass.");
        } else if (yourThrow == monsterThrow) {
            System.out.println("You and the monster rolled the same value. Reroll");
            monserKamp(true);
        } else {
            System.out.println("The monster bested you! He will re-roll for a gold coin.");
            gold--;
            numberOfGold--;
            monserKamp(true);
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
