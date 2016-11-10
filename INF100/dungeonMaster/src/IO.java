import java.util.Scanner;

/**
 * Created by Kristian Os on 03.11.2016.
 */

public class IO {
    static Scanner input = new Scanner(System.in);
    public static String in(){
        return input.nextLine();
    }
    public static void out(String string){
        System.out.println(string);
    }
}
