package faeteam3.Bezugsperson.Bezugsperson.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Bezugsperson")
public class Bezugsperson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bp_id;

    private String bp_id_ext;

    /* TODO nicht sicher ob überhaupt benötigt
    @Embedded
    private Kontaktdaten kontaktdaten;
    */

    @Embedded
    private List<Long> anwesenheitList = new ArrayList<>();

    @Embedded
    private List<Kommunikationskanal> kommunikationskanalList = new ArrayList<Kommunikationskanal>();



    public Bezugsperson() {
    }

    public Bezugsperson(String bp_id_ext) {
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
        if (anwesenheitList == null) {
            anwesenheitList = new ArrayList<>();
        }
        anwesenheitList.add(anwesenheit.getAnw_id());
    }

    public void removeAnwesenheit(Anwesenheit anwesenheit) {
        anwesenheitList.remove(anwesenheit);
    }

    public Long getBp_id() {
        return bp_id;
    }

    public String getBp_id_ext() {
        return bp_id_ext;
    }

    public List<Long> getAnwesenheitList() {
        return anwesenheitList;
    }

    public List<Kommunikationskanal> getKommunikationskanalList() {
        return kommunikationskanalList;
    }
}
