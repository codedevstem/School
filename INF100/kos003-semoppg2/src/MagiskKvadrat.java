import java.util.Scanner;

/**
 * Created by Kristian Os on 07.10.2016.
 */
public class MagiskKvadrat {
    static final int SIZE_OF_SQUARE = 4;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] square = new int[SIZE_OF_SQUARE][SIZE_OF_SQUARE];
        System.out.println("Enter the values of the square:");
        for (int i = 0; i < SIZE_OF_SQUARE; i++)
            for (int j = 0; j < SIZE_OF_SQUARE; j++) {
                System.out.println("Square " + (j+1) + ", " + (i+1));
                square[j][i] = input.nextInt();
            }
        if (erMagiskKvadrat(square)) System.out.println("This is a magical square.");
        else System.out.println("This is not a magical square.");

    }

    private static boolean erMagiskKvadrat(int[][] square) {
        int magicSum = 0;
        int sum;
        for (int i = 0; i < SIZE_OF_SQUARE; i++) {
            sum = 0;
            for (int j = 0; j < SIZE_OF_SQUARE; j++) {
                sum += square[j][i];

            }
            if (magicSum == 0) magicSum = sum;
            else if (magicSum != sum) return false;
            sum = 0;
            for (int j = 0; j < SIZE_OF_SQUARE; j++) {
                sum += square[i][j];
            }
            if (magicSum == 0) magicSum = sum;
            else if (magicSum != sum) return false;
        }

        sum = 0;
        for (int i = 0; i < SIZE_OF_SQUARE; i++) {
            sum += square[i][i];
        }
        if (magicSum == 0) magicSum = sum;
        else if (magicSum != sum) return false;
        sum = 0;
        int diagonalIndex = 3;
        for (int i = 0; i < SIZE_OF_SQUARE; i++) {
            sum += square[i][diagonalIndex];
            diagonalIndex--;
        }
        if (magicSum == 0) magicSum = sum;
        else if (magicSum != sum) return false;
        return true;
    }
}
