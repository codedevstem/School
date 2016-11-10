/**
 * Created by Kristian Os on 28.10.2016.
 */
public class Postpakke {
    String navn;
    String addresse;
    int postnummer;
    double vekt;
    // Default constructor.
    public Postpakke(String navn, String addresse, int postnummer, double vekt) {
        this.navn = navn;
        this.addresse = addresse;
        this.postnummer = postnummer;
        this.vekt = vekt;
    }
    // there is no point in writing code for what getters and setters do.
    public String getNavn() {
        return navn;
    }

    public String getAddresse() {
        return addresse;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public double getVekt() {
        return vekt;
    }
    // prints out the object with the structure that is needed.
    @Override
    public String toString() {
        return  "Receivers name: " + navn + "\n" +
                "Receivers address: " + addresse + "\n" +
                "Receivers post number: " + postnummer + "\n" +
                "Packages weight: " + vekt +
                '\n';
    }
}
