import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TestRaspored extends JFrame {

    private final int sirina = 600;
    private final int visina = 500;

    private JTextArea polje;
    private JButton prikaziSveDugme, prikaziPoSmeniDugme, ocistiRasporedDugme, dodajCasDugme;
    private JComboBox<String> smenaCombo;
    private Raspored raspored;

    public TestRaspored(Raspored raspored) {
        //klasican deo, nema potrebe za objasnjavanjem

        setTitle("Test rasporeda časova");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(400, 100, sirina, visina);

        String[] smene = {"Prva smena", "Druga smena"};
        smenaCombo = new JComboBox<>(smene);
        smenaCombo.setBounds(20, 20, 150, 25);
        add(smenaCombo);

        prikaziSveDugme = new JButton("Prikaži ceo raspored");
        prikaziSveDugme.setBounds(200, 20, 180, 25);
        add(prikaziSveDugme);

        prikaziPoSmeniDugme = new JButton("Prikaži časove po smeni");
        prikaziPoSmeniDugme.setBounds(400, 20, 180, 25);
        add(prikaziPoSmeniDugme);

        dodajCasDugme = new JButton("Dodaj čas");
        dodajCasDugme.setBounds(200, 60, 120, 25);
        add(dodajCasDugme);

        this.raspored = raspored;

        ocistiRasporedDugme = new JButton("Obriši raspored");
        ocistiRasporedDugme.setBounds(20, 60, 150, 25);
        add(ocistiRasporedDugme);

        polje = new JTextArea();
        JScrollPane scrollPolje = new JScrollPane(polje, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPolje.setBounds(20, 100, 560, 350);
        add(scrollPolje);



        prikaziSveDugme.addActionListener(new ActionListener() { // za prikazivanje svih casova
            @Override
            public void actionPerformed(ActionEvent e) {
                polje.setText("");
                ArrayList<Cas> casovi = raspored.getCasovi();
                if (casovi.isEmpty()) {
                    polje.append("Nema zakazanih časova.\n");
                    return;
                }
                polje.append("=== Ceo raspored časova ===\n");
                for (Cas c : casovi) {
                    polje.append(c.toString() + "\n");
                }
            }
        });

        prikaziPoSmeniDugme.addActionListener(new ActionListener() { // za prikazivanje po smeni
            @Override
            public void actionPerformed(ActionEvent e) {
                int izabranaSmena = smenaCombo.getSelectedIndex() + 1; // prva ili druga
                polje.setText("");
                ArrayList<Cas> casovi = raspored.getCasovi();
                boolean pronadjen = false;
                for (Cas c : casovi) {
                    if (c.getSmena() == izabranaSmena) {
                        polje.append(c.toString() + "\n");
                        pronadjen = true;
                    }
                }
                if (!pronadjen) {
                    polje.append("Nema časova za " + (izabranaSmena == 1 ? "prvu smenu.\n" : "drugu smenu.\n"));
                }
            }
        });

        dodajCasDugme.addActionListener(new ActionListener() { // ubedljivo najtezi deo
            @Override
            public void actionPerformed(ActionEvent e) {
                // kreiramo tekstualna polja i padajucu listu za unos podataka
                //sve sto nam je potrebno za jedan cas, svi potrebni podaci
                JTextField imePolje = new JTextField();
                JTextField prezimePolje = new JTextField();
                JTextField predmetPolje = new JTextField();
                JTextField odeljenjePolje = new JTextField();
                JTextField ucionicaPolje = new JTextField();
                JComboBox<String> smenaBox = new JComboBox<>(new String[] {"1", "2"}); // izbor smene
                JComboBox<Dan> danBox = new JComboBox<>(Dan.values()); // izbor dana iz enum


                //pravimo panel koji koristimo u JOption
                JPanel panel = new JPanel(); // dodatni panel
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // elementi na panelu se redjaju po Y osi
                //stavke na panelu
                panel.add(new JLabel("Ime nastavnika:")); panel.add(imePolje);
                panel.add(new JLabel("Prezime nastavnika:")); panel.add(prezimePolje);
                panel.add(new JLabel("Predmet:")); panel.add(predmetPolje);
                panel.add(new JLabel("Odeljenje:")); panel.add(odeljenjePolje);
                panel.add(new JLabel("Učionica (oznaka):")); panel.add(ucionicaPolje);
                panel.add(new JLabel("Smena:")); panel.add(smenaBox);
                panel.add(new JLabel("Dan:")); panel.add(danBox);

                int result = JOptionPane.showConfirmDialog(
                        null, panel, "Dodavanje časa", JOptionPane.OK_CANCEL_OPTION); // potvrda da se ugasi JOption

                if (result == JOptionPane.OK_OPTION) { // ako je kliknuto probamo
                    try {
                        String ime = imePolje.getText().trim();
                        String prezime = prezimePolje.getText().trim();
                        String predmet = predmetPolje.getText().trim();
                        String odeljenjeNaziv = odeljenjePolje.getText().trim();
                        String ucionicaOznaka = ucionicaPolje.getText().trim();

                        if (ime.isEmpty() || prezime.isEmpty() || predmet.isEmpty()
                                || odeljenjeNaziv.isEmpty() || ucionicaOznaka.isEmpty()) { // pazimo da li je sve popunjeno
                            JOptionPane.showMessageDialog(null,
                                    "Sva polja moraju biti popunjena!",
                                    "Greška", JOptionPane.ERROR_MESSAGE); // ako nije greska
                            return; // nastavljamo dalje
                        }

                        int smena = Integer.parseInt((String) smenaBox.getSelectedItem());
                        Dan dan = (Dan) danBox.getSelectedItem(); // lista za dane i za smenu


                        //pravimo sve sto nam je potrebno
                        Nastavnik nastavnik = new Nastavnik(ime, prezime, predmet);
                        Odeljenje odeljenje = new Odeljenje(odeljenjeNaziv);
                        Ucionica ucionica = new Ucionica(ucionicaOznaka);
                        Cas cas = new Cas(odeljenje, nastavnik, ucionica, smena, dan);

                        raspored.dodajCas(cas); // dodajemo cas
                        JOptionPane.showMessageDialog(null, "Čas uspešno dodat!"); // cas je uspesno dodat
                    } catch (Exception ek) { // ako je exception
                        JOptionPane.showMessageDialog(null,
                                "Greška pri unosu: " + ek.getMessage(),
                                "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });




        ocistiRasporedDugme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raspored.ocistiRaspored();
                polje.setText("Raspored je obrisan.\n");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        // kreiramo sta nam je potrebno od casova
        Raspored raspored = new Raspored();

        Nastavnik n1 = new Nastavnik("Dragan", "Veljković", "OOP");
        Nastavnik n2 = new Nastavnik("Jelena", "Jovanović", "Srpski jezik");

        Odeljenje o1 = new Odeljenje("III-5");
        Odeljenje o2 = new Odeljenje("II-2");

        Ucionica u1 = new Ucionica("1");
        Ucionica u2 = new Ucionica("2");

        Cas c1 = new Cas(o1, n1, u1, 1, Dan.CETVRTAK);
        Cas c2 = new Cas(o2, n2, u2, 2, Dan.SREDA);
        Cas c3 = new Cas(o1, n2, u2, 1, Dan.SREDA);
        Cas c4 = new Cas(o2, n1, u1, 2, Dan.UTORAK);

        raspored.dodajCas(c1);
        raspored.dodajCas(c2);
        raspored.dodajCas(c3);
        raspored.dodajCas(c4);

        new TestRaspored(raspored);
    }
}
