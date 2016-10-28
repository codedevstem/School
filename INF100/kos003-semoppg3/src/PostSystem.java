import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Kristian Os on 28.10.2016.
 */

public class PostSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Postpakke> packages = new ArrayList<>();
        System.out.println("How many packages should be registered");
        int numberOfPackages = Integer.parseInt(input.nextLine());
        for (int i = 1; i <= numberOfPackages; i++) {
            System.out.println("Package " + i + "\nGive receivers name");
            String tempNavn = input.nextLine();
            System.out.println("Give receivers address");
            String tempAddress = input.nextLine();
            System.out.println("Give receivers postNumber");
            int tempPostNumber = Integer.parseInt(input.nextLine());
            System.out.println("Give package weight");
            double tempWeight = Double.parseDouble(input.nextLine());
            packages.add(new Postpakke(tempNavn, tempAddress, tempPostNumber, tempWeight));
        }
        System.out.println("Packages:");
        int counter = 1;
        for (Postpakke p : packages) {
            System.out.print("Package" + counter + "\n" + p.toString());
            counter++;
        }
    }
}
