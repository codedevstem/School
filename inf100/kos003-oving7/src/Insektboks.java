import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Kristian Os on 18.11.2016.
 */
public class Insektboks {
    static int bugAtSamePos = 0;
    public static void main(String[] args) {
        int numberOfBugs = 0, numberOfIterations = 0;
        ArrayList<Insekt> bugs = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of insects (at least one):");
        try {
            numberOfBugs = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("Enter number of iterations (at least one)");
        try {
            numberOfIterations = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int randomX;
        int randomY;
        for (int i = 0; i < numberOfBugs; i++) {
            do {
                randomX = (int) (Math.random() * 21);
                randomY = (int) (Math.random() * 21);
            } while (bugAtPosition(bugs,randomX, randomY));
            bugs.add(new Insekt(randomX, randomY));
        }
        for (int i = 1; i <= numberOfIterations; i++) {
            System.out.println("-------------\nIteration " + i);
            int randomRightBug;
            int randomLeftBug;
            do {
                randomRightBug = (int)(Math.random()*(bugs.size()));
                randomLeftBug = (int)(Math.random()*bugs.size());
            } while (randomRightBug == randomLeftBug);
            bugs.get(randomRightBug).snuMotHoeyre();
            bugs.get(randomLeftBug).snuMotVenstre();
            bugs.forEach(bug -> {
                bug.bevegFremover();
                System.out.println(bug.toString());
            });
        }
    }

    private static boolean bugAtPosition(ArrayList<Insekt> bugs, int randomX, int randomY) {
        for (Insekt bug : bugs)
            if (bug.getxKord() == randomX && bug.getyKord() == randomY) return true;
        return false;
    }

}
