import java.util.Scanner;
public class Primtallfaktorering {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = 0, currentFactor;
        System.out.println("Write an integer larger than 0");
        try{number = Integer.parseInt(input.nextLine());
        }catch (NumberFormatException e) {e.printStackTrace();}
        if (number <= 0) System.out.println("Number is below zero.");
        while((currentFactor = finnMinsteDivisor(number)) != number && number != 1){
            number = number / currentFactor;
            System.out.println(currentFactor);
        }
        System.out.println(number);
    }
    private static int finnMinsteDivisor(int number) {
        System.out.print(number + " " );
        if (number%2==0) return 2;
        for(int i = 3; i <= number; i+=2)
            if(number%i==0) return i;
        return -1;
    }
}
