import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kristian Os on 07.10.2016.
 */
public class YatzyToSpiller {
    static Random dice = new Random();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        // Initialize the necessary local variables
        String[] players = new String[2];
        String winner = "";
        int[] diceValues, totalScore = new int[2];
        int roundScore, numberOfRolls = 0, maxRerolls = 2, choice, reRolls, winnerScore = 0;
        // Taking in the name of the different players

        for (int i = 0; i < players.length; i++) {
            System.out.println("Give the name of player " + (i+1) + ":");
            players[i] = input.nextLine();
        }
        // Create main game-loop
        while (numberOfRolls < 5) {
            // Loop to control which player that has the turn
            for (int j = 0; j < players.length; j++) {
                System.out.println(players[j] + " must throw the dices:");
                diceValues = new int[5];
                System.out.println("Round " + (numberOfRolls + 1));
                for (int i = 0; i < 5; i++) {
                    diceValues[i] = terningkast();
                }
                System.out.println(printOutRound(diceValues));

                reRolls = 1;
                // The re-roll loop
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
                    if (choice < 0 || choice > 5) {
                        System.out.print("Error: Number must be between 0-5.");
                        System.exit(11);
                    /*Ignore this code as long as input is not zero and
                     maximum re-rolls has not been reached*/
                    } else if (choice != 0 && reRolls != maxRerolls) {
                        // Generate new roll for the given position
                        diceValues[choice - 1] = terningkast();
                        // Print out the new roundRoll.
                        System.out.println(printOutRound(diceValues));
                        reRolls++;
                    } else {
                        // same as last submission.
                        roundScore = poengForTerning(diceValues);
                        // add score of this round to the current player.
                        totalScore[j] += roundScore;
                        System.out.println("Score: " + roundScore);
                        break;
                    }
                } while (true);
            }
            numberOfRolls++;
        }
        // Determine winner loop (Could be different method in case of several games)
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i] + " has " + totalScore[i] + " points.");
            if (winnerScore < totalScore[i]) {
                winnerScore = totalScore[i];
                winner = players[i];
            }
        }
        System.out.println(winner + " won.");

    }

    /**
     * Generates a formated string containing the dice values of this round.
     * @param diceArray An array with dice values
     * @return a string with the scores of this round
     */
    private static String printOutRound(int[] diceArray) {
        String scoreBoard = "";
        for (int i = 0; i < diceArray.length; i++) {
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
     * Calculates the sum of the current round of dice values.
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
