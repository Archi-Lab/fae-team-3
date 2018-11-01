package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
public class Kommunikationskanal {

    public Kommunikationskanal(String kanalBezeichnung, Kommunikationsart kommunikationsart, String techAdresse) {
        this.kanalBezeichnung = kanalBezeichnung;
        this.kommunikationsart = kommunikationsart;
        this.techAdresse = techAdresse;
    }

    private String kanalBezeichnung;

    @Enumerated
    private Kommunikationsart kommunikationsart;

    /*
        die technische Adresse, bspw Handynummer oder E-Mail Adresse
    */
    private String techAdresse;





    public String getKanalBezeichnung() {
        return kanalBezeichnung;
    }

    public Kommunikationsart getKommunikationsart() {
        return kommunikationsart;
    }

    public String getTechAdresse() {
        return techAdresse;
    }

    public void setKanalBezeichnung(String kanalBezeichnung) {
        this.kanalBezeichnung = kanalBezeichnung;
    }

    public void setTechAdresse(String techAdresse) {
        this.techAdresse = techAdresse;
    }
}
