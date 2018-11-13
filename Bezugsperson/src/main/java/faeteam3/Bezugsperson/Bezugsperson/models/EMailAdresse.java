package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Embeddable;

@Embeddable
public class EMailAdresse extends TechAdresse {

    public EMailAdresse(String eMailAdresse) {
        this.eMailAdresse = eMailAdresse;
    }

    private String eMailAdresse;

    public String geteMailAdresse() {
        return eMailAdresse;
    }
}
