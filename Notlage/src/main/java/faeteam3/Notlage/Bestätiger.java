package faeteam3.Notlage;

import javax.persistence.Embeddable;

@Embeddable
public class Bestätiger {
	
	private Long bestätigerID;
	
	protected Bestätiger()
	{
		
	}
	
	public Bestätiger(Long uuid)
	{
		this.bestätigerID = uuid;
	}
	
	private Long getUUID()
	{
		return bestätigerID;
	}

}
