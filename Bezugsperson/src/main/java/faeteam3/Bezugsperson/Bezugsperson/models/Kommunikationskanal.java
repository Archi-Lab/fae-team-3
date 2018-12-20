package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


//@Embeddable
@Entity(name="Kommunikationskanal")
public class Kommunikationskanal {

	public Kommunikationskanal() {
		
	}
 	
    public Kommunikationskanal(String kanalBezeichnung, Kommunikationsart kommunikationsart, TechAdresse techAdresse) {
        this.kanalBezeichnung = kanalBezeichnung;
        this.kommunikationsart = kommunikationsart;
        this.techAdresse = techAdresse;
    }

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	private String id;
    
    private String kanalBezeichnung;

    @Enumerated
    private Kommunikationsart kommunikationsart;

    @Embedded
    private TechAdresse techAdresse;

    public String getKanalId() {
    	return id;
    }

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
