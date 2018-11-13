package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Enumerated;

@Embeddable
public class Kommunikationskanal {

    public Kommunikationskanal(String kanalBezeichnung, Kommunikationsart kommunikationsart, TechAdresse techAdresse) {
        this.kanalBezeichnung = kanalBezeichnung;
        this.kommunikationsart = kommunikationsart;
        this.techAdresse = techAdresse;
    }

    private final String kanalBezeichnung;

    @Enumerated
    private final Kommunikationsart kommunikationsart;

    @Embedded
    private final TechAdresse techAdresse;


    public String getKanalBezeichnung() {
        return kanalBezeichnung;
    }

    public Kommunikationsart getKommunikationsart() {
        return kommunikationsart;
    }

    public TechAdresse getTechAdresse() {
        return techAdresse;
    }
}
