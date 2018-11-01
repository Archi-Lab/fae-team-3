package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Embeddable;

@Embeddable
public class Kontaktdaten {

    public Kontaktdaten() {

    }

    public Kontaktdaten(String vorname, String nachname, String adresse, String telefonnummer) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
    }

    private String vorname;
    private String nachname;
    private String adresse;
    private String telefonnummer;

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
}
