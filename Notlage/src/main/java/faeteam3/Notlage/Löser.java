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
		this.löserID = uuid;
	}
	
	private Long getUUID()
	{
		return löserID;
	}

}
