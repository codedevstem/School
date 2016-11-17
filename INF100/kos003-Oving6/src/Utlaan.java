/**
 * Created by Kristian Os on 15.11.2016.
 */
public class Utlaan {
    Bok bok;
    String name;

    public Utlaan(Bok bok, String name) {
        this.bok = bok;
        this.name = name;
    }

    public Bok getBok() {
        return bok;
    }

    public void setBok(Bok bok) {
        this.bok = bok;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Utlaan{" +
                "bok=" + bok +
                ", name='" + name + '\'' +
                '}';
    }
}
