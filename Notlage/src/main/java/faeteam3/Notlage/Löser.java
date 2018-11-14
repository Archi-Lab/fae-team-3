package faeteam3.Notlage;

import javax.persistence.Embeddable;

@Embeddable
public class Löser {
	
	private Long löserID;
	
	protected Löser()
	{
		
	}
	
	public Löser(Long uuid)
	{
		this.setLöserID(uuid);
	}

	public Long getLöserID() {
		return löserID;
	}

	public void setLöserID(Long löserID) {
		this.löserID = löserID;
	}
	


}
