/**
 * Created by Kristian Os on 11.11.2016.
 */
public class Bok implements Comparable<Bok>{
    private String ISBN;
    private String forfatternavn;
    private String tittel;
    private int antall;
    public Bok(String ISBN, String forfatternavn, String tittel, int antall) {
        this.ISBN = ISBN;
        this.forfatternavn = forfatternavn;
        this.tittel = tittel;
        this.antall = antall;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public String getForfatternavn() {
        return forfatternavn;
    }
    public void setForfatternavn(String forfatternavn) {
        this.forfatternavn = forfatternavn;
    }
    public String getTittel() {
        return tittel;
    }
    public void setTittel(String tittel) {
        this.tittel = tittel;
    }
    public int getAntall() {
        return antall;
    }
    public void setAntall(int antall) {
        this.antall = antall;
    }
    public int compareTo(Bok o) {
        int reverse = 1;
        if (this.getForfatternavn().compareTo(o.getForfatternavn()) != 0)
            return this.getForfatternavn().compareTo(o.getForfatternavn()) * reverse;
        else
            if (this.getTittel().compareTo(o.getTittel()) != 0)
                return this.getTittel().compareTo(o.getTittel()) * reverse;
            else
                if (this.getISBN().compareTo(o.getISBN()) != 0)
                    return this.getISBN().compareTo(o.getISBN()) * reverse;
                else
                    return 0;
    }
    @Override
    public String toString() {
        return "Bok{" +
                "ISBN='" + ISBN + '\'' +
                ", forfatternavn='" + forfatternavn + '\'' +
                ", tittel='" + tittel + '\'' +
                ", antall=" + antall +
                '}';
    }
}
