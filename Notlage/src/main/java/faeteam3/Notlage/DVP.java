package faeteam3.Notlage;

import javax.persistence.Embeddable;

@Embeddable
public class DVP {
	
	private Long uuid;
	
	public DVP(Long id)
	{
		uuid =id;
	}
	
	public Long getUUID()
	{
		return uuid;
	}
	
	@Override
	public String  toString()
	{
		return String.valueOf(uuid);
	}

}
