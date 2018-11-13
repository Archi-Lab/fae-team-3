package faeteam3.Bezugsperson.Bezugsperson.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Bezugsperson")
public class Bezugsperson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bp_id;

    private final String bp_id_ext;

    /* TODO nicht sicher ob überhaupt benötigt
    @Embedded
    private Kontaktdaten kontaktdaten;
    */

    @Embedded
    private List<Anwesenheit> anwesenheitList;

    @Embedded
    private List<Kommunikationskanal> kommunikationskanalList;


    public Bezugsperson(String bp_id_ext) {
        kommunikationskanalList = new ArrayList<Kommunikationskanal>();
        anwesenheitList = new ArrayList<Anwesenheit>();
        this.bp_id_ext = bp_id_ext;
    }


    public void addKommunikationskanal(Kommunikationskanal kommunikationskanal) {
        kommunikationskanalList.add(kommunikationskanal);
    }

    public void removeKommunikationskanal(String kanalbezeichnung) {
        for (Kommunikationskanal kommunikationskanal : kommunikationskanalList) {
            if (kommunikationskanal.getKanalBezeichnung().equals(kommunikationskanal)) {
                kommunikationskanalList.remove(kommunikationskanal);
                return;
            }
        }
    }

    public void addAnwesenheit(Anwesenheit anwesenheit) {
        anwesenheitList.add(anwesenheit);
    }

    public void removeAnwesenheit(Anwesenheit anwesenheit) {
        anwesenheitList.remove(anwesenheit);
    }

    public Long getBp_id() {
        return bp_id;
    }

    public List<Anwesenheit> getAnwesenheitList() {
        return anwesenheitList;
    }

    public List<Kommunikationskanal> getKommunikationskanalList() {
        return kommunikationskanalList;
    }
}
