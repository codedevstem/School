import java.util.Random;

/**
 * Created by Kristian Os on 06.10.2016.
 */
public class YatzyEnkel {
    static Random terning = new Random();
    public static void main(String[] args) {
        int totalScore = 0, roundScore = 0, numberOfRolls = 0;
        int[] diceValues;
        // Starting main gameLoop
        while (numberOfRolls < 5) {
            // Initializing empty array for this round
            diceValues = new int[5];
            System.out.println("Round " + (numberOfRolls+1));
            // Generating dice-rolls for this turn
            for (int i = 0; i < 5; i++) {
                // Generating the current dice-throw
                diceValues[i] = terningkast();
                System.out.println("Dice roll " + (i+1) + ": " + diceValues[i]);
            }
            // Calculating this turns score by calling the "poengForTerning" method
            roundScore = poengForTerning(diceValues);
            System.out.println("Score: " + roundScore);
            totalScore += roundScore;
            numberOfRolls++;
        }
        System.out.println("Total score: " + totalScore);
    }

    private static int terningkast() {
        return terning.nextInt(6)+1;
    }

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
