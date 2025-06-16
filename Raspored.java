import java.util.ArrayList;

public class Raspored {
    private ArrayList<Cas> casovi; // lista gde smestamo casove

    public Raspored() {
        this.casovi = new ArrayList<>();
    }

    public void dodajCas(Cas cas) { // dodavanje casa na raspored
        casovi.add(cas);
    }

    public ArrayList<Cas> getCasovi() {
        return casovi;
    }

    public void ocistiRaspored() { // cistimo
        casovi.clear();
    }
}
