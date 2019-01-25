package faeteam3.Bezugsperson.Bezugsperson.models;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Bezugsperson")
public class Bezugsperson {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid")
    private String bp_id;

    private String bp_id_ext;

    /* TODO nicht sicher ob überhaupt benötigt
    @Embedded
    private Kontaktdaten kontaktdaten;
    */

    @OneToMany
    private List<Anwesenheit> anwesenheitList = new ArrayList<Anwesenheit>();


    @OneToMany
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
            if (kommunikationskanal.getKanalBezeichnung().equals(kommunikationskanal.getKanalBezeichnung())) {
                kommunikationskanalList.remove(kommunikationskanal);
                return;
            }
        }
    }

    public void addAnwesenheit(Anwesenheit anwesenheit) {
        if (anwesenheitList == null) {
            anwesenheitList = new ArrayList<>();
        }
        anwesenheitList.add(anwesenheit);
    }

    public void removeAnwesenheit(Anwesenheit anwesenheit) {
        anwesenheitList.remove(anwesenheit);
    }

    public String getBp_id() {
        return bp_id;
    }

    public String getBp_id_ext() {
        return bp_id_ext;
    }

    public List<Anwesenheit> getAnwesenheitList() {
        return anwesenheitList;
    }

    public List<Kommunikationskanal> getKommunikationskanalList() {
        return kommunikationskanalList;
    }
}
