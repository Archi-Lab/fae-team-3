package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Embeddable
public class TechAdresse {
	
	public TechAdresse() {
		
	}
	
	public TechAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	String adresse;
    
	String getAdresse() {
		return adresse;
	}
	
}
