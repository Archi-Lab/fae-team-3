package faeteam3.Bezugsperson.Bezugsperson.models;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class EMailAdresse extends TechAdresse {

 
	public EMailAdresse(String eMailAdresse) {
		super("foo");
		check(eMailAdresse);
		super.adresse = eMailAdresse;
	}
	
	private void check(String s) {
		//TODO
	}
 	
}
