import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kristian Os on 06.10.2016.
 *
 * @note Could have used an array-list as well, but for this there is no need.
 */
public class YatzyOmkast {
    /*creating static objects so that we can use them in the other
    methods without passing them as arguments*/
    static Random dice = new Random();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int totalScore = 0, roundScore, numberOfRolls = 0, maxRerolls = 2, choice, reRolls;
        int[] diceValues;
        // Starting the main game loop
        while (numberOfRolls < 5) {

            // Initializing a new empty array for each round
            diceValues = new int[5];
            System.out.println("Round " + (numberOfRolls+1));
            // Rolling the dices
            for (int i = 0; i < 5; i++) {
                // Generating dice value
                diceValues[i] = terningkast();
            }
            System.out.println(printOutRound(diceValues));

            // Reset re-rolls
            reRolls = 1;
            // Re-roll loop
            do {
                System.out.println("Re-roll " + reRolls + ". Write dice number (1-5) to re-roll, " +
                        "or 0 to keep all dices as they are:");
                //Check that the next number is of type int.
                if (!input.hasNextInt()) {
                    System.out.print("Error: Number must be a number.");
                    // Exit with error code 0 (The code zero is for debugging
                    System.exit(10);
                }
                choice = input.nextInt();
                // Check that the input is in the interval [0-5]
                if (choice < 0 || choice > 5){
                    System.out.print("Error: Number must be between 0-5.");
                    System.exit(11);
                /*Ignore this code as long as input is not zero and
                 maximum re-rolls has not been reached*/
                }else if ( choice != 0 && reRolls != maxRerolls){
                    // Generate new roll for the given position
                    diceValues[choice-1] = terningkast();
                    // Print out the new roundRoll.
                    System.out.println(printOutRound(diceValues));
                    reRolls++;
                } else {
                    // same as last submission.
                    roundScore = poengForTerning(diceValues);
                    totalScore += roundScore;
                    System.out.println("Score: " + roundScore);
                    break;
                }
            } while (true);
            numberOfRolls++;
        }
        System.out.println("Total score: " + totalScore);
    }

    /**
     *
     * @param diceArray An array with dice values
     * @return a string with the scores of this round
     */
    private static String printOutRound(int[] diceArray) {
        String scoreBoard = "";
        for (int i = 0; i < diceArray.length; i++) {
            // Checks if this is the last roll in the array.
            if (i != diceArray.length-1)
                scoreBoard += "Dice roll " + (i+1) + ": " + diceArray[i] + "\n";
            else
                scoreBoard += "Dice roll " + (i+1) + ": " + diceArray[i];
        }
        return scoreBoard;
    }

    /**
     * Generates a random number simulating a d6 roll
     * @return
     */
    private static int terningkast() {
        return dice.nextInt(6)+1;
    }

    /**
     *
     * @param diceArray An array with dice values
     * @return The sum of this rounds dices based on the highest elements.
     */
    private static int poengForTerning(int[] diceArray) {
        int numberSum, highestSum = 0;
        for (int i = 1; i <= 6; i++) {
            numberSum = 0;
            for (int j = 0; j < diceArray.length; j++) {
                if (i == diceArray[j])numberSum += diceArray[j];
            }
            if (highestSum < numberSum) highestSum = numberSum;
        }
        return highestSum;
    }
}
