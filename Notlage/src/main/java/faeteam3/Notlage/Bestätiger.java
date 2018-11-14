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
		this.setBestätigerID(uuid);
	}

	public Long getBestätigerID() {
		return bestätigerID;
	}

	public void setBestätigerID(Long bestätigerID) {
		this.bestätigerID = bestätigerID;
	}

}
