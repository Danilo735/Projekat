public class Cas {
    private final Dan dan;
    private Odeljenje odeljenje;
    private Nastavnik nastavnik;
    private Ucionica ucionica;
    private int smena; // 1 = prva smena, 2 = druga smena

    public Cas(Odeljenje odeljenje, Nastavnik nastavnik, Ucionica ucionica, int smena, Dan dan) {
        this.odeljenje = odeljenje;
        this.nastavnik = nastavnik;
        this.ucionica = ucionica;
        this.smena = smena;
        this.ucionica.rezervisi(); // ucionica se automatski rezervise pri kreiranju casa
        this.dan = dan;
    }

    public void otkazi() {
        ucionica.oslobodi(); // Oslobadja ucionicu ako se cas otkaze
    }

    public String getSmenaString() {
        return (smena == 1) ? "Prva smena" : "Druga smena";
    }

    public String prikaziCas() {// radi lakseg snalazenja
        return getSmenaString() + " - " + odeljenje + " - " + nastavnik + " - " + ucionica.getOznaka() + " - " + dan;
    }
    public int getSmena() {
        return smena;
    }


    @Override
    public String toString() {
        return prikaziCas();
    }
}
