public abstract class Osoba {
    protected String ime;
    protected String prezime;

    public Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getPunoIme() {
        return ime + " " + prezime;
    }
}
