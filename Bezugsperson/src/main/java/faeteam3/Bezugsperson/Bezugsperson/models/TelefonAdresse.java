package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Embeddable;

@Embeddable
public class TelefonAdresse extends TechAdresse {

    public TelefonAdresse(String telefonNummer) {
    	super("foo");
        this.telefonNummer = telefonNummer;
    }

    private String telefonNummer;

    public String getTelefonNummer() {
        return telefonNummer;
    }
}
