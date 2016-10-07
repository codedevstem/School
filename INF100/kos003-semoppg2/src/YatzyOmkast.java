import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kristian Os on 06.10.2016.
 */
public class YatzyOmkast {
    static Random dice = new Random();
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int[] diceValues;
        int totalScore = 0;
        int roundScore = 0;
        int numberOfRolls = 0;
        int maxRerolls = 3;
        int choice;
        while (numberOfRolls < 5) {
            diceValues = new int[5];
            System.out.println("Round " + (numberOfRolls+1));
            for (int i = 0; i < 5; i++) {
                diceValues[i] = terningkast();
            }
            System.out.println(printOutRound(diceValues));
            for (int i = 1; i <= maxRerolls; i++) {
                if (i == 3){
                    roundScore = poengForTerning(diceValues);
                    totalScore += roundScore;
                    System.out.println("Score: " + roundScore);
                    numberOfRolls++;
                    break;
                }
                System.out.println("Re-roll " + i + ". Write dice number (1-5) to re-roll, " +
                        "or 0 to keep all dices as they are:");
                if (!input.hasNextInt()) {
                    System.out.print("Error: Number must be a number.");
                    System.exit(0);
                }
                choice = input.nextInt();
                if (choice < 0 || choice > 5){
                    System.out.print("Error: Number must be between 0-5.");
                    System.exit(0);
                }else if ( choice == 0){
                    roundScore = poengForTerning(diceValues);
                    totalScore += roundScore;
                    System.out.println("Score: " + roundScore);
                    numberOfRolls++;
                } else {
                    diceValues[choice-1] = terningkast();
                    System.out.println(printOutRound(diceValues));
                }
            }
        }
        System.out.println("Total score: " + totalScore);
    }

    private static String printOutRound(int[] diceValue) {
        String scoreBoard = "";
        for (int i = 0; i < diceValue.length; i++) {
            if (i != diceValue.length-1)
                scoreBoard += "Dice roll " + (i+1) + ": " + diceValue[i] + "\n";
            else
                scoreBoard += "Dice roll " + (i+1) + ": " + diceValue[i];
        }
        return scoreBoard;
    }

    private static int terningkast() {
        return dice.nextInt(6)+1;
    }

    private static int poengForTerning(int[] diceValues) {
        int numberSum, highestSum = 0;
        for (int i = 1; i <= 6; i++) {
            numberSum = 0;
            for (int j = 0; j < diceValues.length; j++) {
                if (i == diceValues[j])numberSum += diceValues[j];
            }
            if (highestSum < numberSum) highestSum = numberSum;
        }
        return highestSum;
    }
}
