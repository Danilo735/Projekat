public class Odeljenje {
    private String oznaka;

    public Odeljenje(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getOznaka() {
        return oznaka;
    }

    @Override
    public String toString() {
        return "Odeljenje " + oznaka;
    }
}