/**
 * Created by Kristian Os on 28.10.2016.
 */
public class Postpakke {
    String navn;
    String addresse;
    int postnummer;
    double vekt;

    public Postpakke(String navn, String addresse, int postnummer, double vekt) {
        this.navn = navn;
        this.addresse = addresse;
        this.postnummer = postnummer;
        this.vekt = vekt;
    }

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

    @Override
    public String toString() {
        return  "Receivers name: " + navn + "\n" +
                "Receivers address: " + addresse + "\n" +
                "Receivers post number: " + postnummer + "\n" +
                "Packages weight: " + vekt +
                '\n';
    }
}
