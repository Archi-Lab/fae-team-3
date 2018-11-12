package faeteam3.Notlage;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Benachrichtigung 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Long dvp_id;
	
	private String informationen;
	
	private boolean wurde_bestätigt=false;
	
	public Benachrichtigung(){}
	
	 
	public Benachrichtigung(Long dvp_id,String informationen)
	{
		this.dvp_id = dvp_id;
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

	public Long get_dvp_id() {
		return dvp_id;
	}

	public void set_dvp_id(Long dvp_id) {
		this.dvp_id = dvp_id;
	}

}

