import java.util.Scanner;

/**
 * Created by Kristian Os on 07.10.2016.
 */
public class MagiskKvadrat {
    // Decale static final variable to use across all methods.
    static final int SIZE_OF_SQUARE = 4;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize a two-dimensional array of size 2^SIZE_OF_SQUARE
        int[][] square = new int[SIZE_OF_SQUARE][SIZE_OF_SQUARE];
        System.out.println("Enter the values of the square:");
        for (int i = 0; i < SIZE_OF_SQUARE; i++)
            for (int j = 0; j < SIZE_OF_SQUARE; j++) {
                System.out.println("Square " + (j+1) + ", " + (i+1));
                //Insert input into the correct square.
                square[j][i] = input.nextInt();
            }
        /*Check if square is magical or not based on the return boolean of "erMagiskKvadrat()"
        and print out message.*/
        if (erMagiskKvadrat(square)) System.out.println("This is a magical square.");
        else System.out.println("This is not a magical square.");

    }

    /**
     * The number is 34 so each of the numbers must be there.
     * @param square The square to be checked.
     * @return True/False based on if it is a magical square.
     */
    private static boolean erMagiskKvadrat(int[][] square) {
        int magicSum = 34, sum;
        boolean hasNumber;
        //Check if all number between 1-16 is there.
        for (int i = 1; i <= 16; i++) {
            hasNumber = false;
            for (int j = 0; j < SIZE_OF_SQUARE; j++) {
                for (int k = 0; k < SIZE_OF_SQUARE; k++) {
                    if (i == square[j][k]) {
                        hasNumber = true;
                    }
                }
            }
            if (!hasNumber) return false;
        }
        // Outer loop
        for (int i = 0; i < SIZE_OF_SQUARE; i++) {
            sum = 0;
            // Inner loop
            for (int j = 0; j < SIZE_OF_SQUARE; j++) {
                // Sum up the current row
                sum += square[j][i];
            }
            // Check if sum from current col equals magicSum
            if (magicSum != sum) return false;
            sum = 0;
            //Inner loop
            for (int j = 0; j < SIZE_OF_SQUARE; j++) {
                // Sum up the current col
                sum += square[i][j];
            }
            // Check if sum from current col equals magicSum
            if (magicSum != sum) return false;
        }
        sum = 0;
        for (int i = 0; i < SIZE_OF_SQUARE; i++) {
            //Sum up diagonal down from [0,0]
            sum += square[i][i];
        }
        // Check if diagonal down from 0 equals magicSum
        if (magicSum != sum) return false;
        sum = 0;
        int diagonalIndex = 3;
        for (int i = 0; i < SIZE_OF_SQUARE; i++) {
            //Sum up diagonal down from [0,3]
            sum += square[i][diagonalIndex];
            diagonalIndex--;
        }
        // Check if diagonal down from [0,3] equals magicSum
        if (magicSum != sum) return false;
        return true;
    }
}
