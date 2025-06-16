public class Nastavnik extends Osoba {
    private String predmet;

    public Nastavnik(String ime, String prezime, String predmet) {
        super(ime, prezime);
        this.predmet = predmet;
    }

    public String getPredmet() {
        return predmet;
    }

    @Override
    public String toString() { // ispis
        return getPunoIme() + " (" + predmet + ")";
    }
}

