/**
 * Created by Kristian Os on 18.11.2016.
 */
public class InsektTest {
    public static void main(String[] args) {
        int nOfExecutions = 0;
        Insekt insekt = new Insekt(10, 10);
        System.out.println(insekt.toString());
        nOfExecutions = 4;
        for (int i = 0; i < nOfExecutions; i++) {
            insekt.bevegFremover();
            System.out.println(insekt.toString());
        }
        insekt.snuMotVenstre();
        System.out.println(insekt.toString());
        nOfExecutions = 3;
        for (int i = 0; i < nOfExecutions; i++) {
            insekt.bevegFremover();
            System.out.println(insekt.toString());
        }
        insekt.snuMotHoeyre();
        System.out.println(insekt.toString());
        nOfExecutions = 3;
        for (int i = 0; i < nOfExecutions; i++) {
            insekt.bevegFremover();
            System.out.println(insekt.toString());
        }
    }
}
