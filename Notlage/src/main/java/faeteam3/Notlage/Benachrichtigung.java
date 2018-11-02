package faeteam3.Notlage;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Benachrichtigung 
{
	
	
	private Long betroffene_Person_id;
	
	private String informationen;
	
	private boolean wurde_bestätigt=false;
	
	public Benachrichtigung(){}
	
	 
	public Benachrichtigung(Long betroffene_Person_id,String informationen)
	{
		this.setBetroffene_Person_id(betroffene_Person_id);
		this.setInformationen(informationen);
		wurde_bestätigt=false;
	}

	public boolean isWurde_bestätigt() {
		return wurde_bestätigt;
	}

	public void setWurde_bestätigt() 
	{
		this.wurde_bestätigt = true;
	}

	public String getInformationen() {
		return informationen;
	}

	public void setInformationen(String informationen) {
		this.informationen = informationen;
	}

	public Long getBetroffene_Person_id() {
		return betroffene_Person_id;
	}

	public void setBetroffene_Person_id(Long betroffene_Person_id) {
		this.betroffene_Person_id = betroffene_Person_id;
	}

}

