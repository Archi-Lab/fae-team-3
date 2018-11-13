package faeteam3.Notlage;

import javax.persistence.Embeddable;

@Embeddable
public class BP 
{
	private Long uuid;
	public BP(Long uuid)
	{
		this.uuid = uuid;
	}
	
	private Long getUUID()
	{
		return uuid;
	}
}
