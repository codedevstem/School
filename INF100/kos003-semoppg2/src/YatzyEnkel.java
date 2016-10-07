import java.util.Random;

/**
 * Created by Kristian Os on 06.10.2016.
 */
public class YatzyEnkel {
    static Random terning = new Random();
    public static void main(String[] args) {

        int[] diceValues;
        int totalScore = 0;
        int roundScore = 0;
        int numberOfRolls = 0;

        while (numberOfRolls < 5) {
            diceValues = new int[5];
            System.out.println("Round " + (numberOfRolls+1));
            for (int i = 0; i < 5; i++) {
                diceValues[i] = terningkast(terning);
                System.out.println("Dice roll " + (i+1) + ": " + diceValues[i]);
            }
            roundScore = poengForTerning(diceValues);
            System.out.println("Score: " + roundScore);
            totalScore += roundScore;
            numberOfRolls++;
        }
        System.out.println("Total score: " + totalScore);
    }

    private static int terningkast(Random terning) {
        return terning.nextInt(6)+1;
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
