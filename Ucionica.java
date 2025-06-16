public class Ucionica {
    private String oznaka;
    private boolean zauzeta;

    public Ucionica(String oznaka) { // konstruktor
        this.oznaka = oznaka;
        this.zauzeta = false;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void rezervisi() {
        this.zauzeta = true;
    }

    public void oslobodi() { // za uklanjanje ucionica
        this.zauzeta = false;
    }

}