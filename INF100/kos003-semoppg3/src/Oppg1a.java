/**
 * Created by Kristian Os on 03.01.2017.
 */
public class Oppg1a {
    public static void main(String[] args) {
        int n = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n / 2 && j == n / 2) {
                    System.out.print("X");
                } else if (i == j) {
                    System.out.print("\\");   /* Kun ett '\' skrives */} else if (i+j == n-1) {System.out.print("/");} else {System.out.print(" ");}}System.out.println();
        }
    }
    public static int lengsteSerie(int[] a) {
        int longestSeries = 0;
        int currentLongest = 0;
        for (int i = 0; i < a.length; i++){
            for(int j = i+1; j < a.length; j++){
                int x = a[i];
                int y = a[j];
                if ((x <= 0 && y >= 0) || (x >= 0 && y <= 0)){
                    if (currentLongest > longestSeries) {
                        longestSeries = currentLongest;
                    }
                    currentLongest = 0;
                }
                else if (x > 0 && y > 0)
                    if(currentSeries == 0) currentSeries = 2;
                    else currentSeries += 1;
                else if (x < 0 && y < 0)
                    if(currentSeries == 0) currentSeries = 2;
                    else currentSeries += 1;
            }
        }
        return longestSeries;
    }
}