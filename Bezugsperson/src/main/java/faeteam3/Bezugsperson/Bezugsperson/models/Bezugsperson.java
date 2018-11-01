package faeteam3.Bezugsperson.Bezugsperson.models;


import javax.persistence.*;
import java.util.List;

@Entity(name = "Bezugsperson")
public class Bezugsperson {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bez_id;

    @Embedded
    private Kontaktdaten kontaktdaten;

    private int prioritaet;
    private int anfragen_insgesamt;
    private int anfragen_beantwortet;

    @Embedded
    private List<Verfuegbarkeit> verfuegbarkeitList;

    @Embedded
    private List<Kommunikationskanal> kommunikationskanalList;


    public Long getBez_id() {
        return bez_id;
    }

    public Kontaktdaten getKontaktdaten() {
        return kontaktdaten;
    }

    public void setKontaktdaten(Kontaktdaten kontaktdaten) {
        this.kontaktdaten = kontaktdaten;
    }

    public int getPrioritaet() {
        return prioritaet;
    }

    public void setPrioritaet(int prioritaet) {
        this.prioritaet = prioritaet;
    }

    public int getAnfragen_insgesamt() {
        return anfragen_insgesamt;
    }

    public void setAnfragen_insgesamt(int anfragen_insgesamt) {
        this.anfragen_insgesamt = anfragen_insgesamt;
    }

    public int getAnfragen_beantwortet() {
        return anfragen_beantwortet;
    }

    public void setAnfragen_beantwortet(int anfragen_beantwortet) {
        this.anfragen_beantwortet = anfragen_beantwortet;
    }

    public List<Verfuegbarkeit> getVerfuegbarkeitList() {
        return verfuegbarkeitList;
    }

    public void setVerfuegbarkeitList(List<Verfuegbarkeit> verfuegbarkeitList) {
        this.verfuegbarkeitList = verfuegbarkeitList;
    }

    public List<Kommunikationskanal> getKommunikationskanalList() {
        return kommunikationskanalList;
    }

    public void setKommunikationskanalList(List<Kommunikationskanal> kommunikationskanalList) {
        this.kommunikationskanalList = kommunikationskanalList;
    }
}
