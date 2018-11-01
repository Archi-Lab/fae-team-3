package faeteam3.Notlage;

import javax.persistence.Embeddable;

@Embeddable
public class Nachricht 
{
	
	public String header;
	public String payload;
	public Long dvp_real_id;
	
	
	public Nachricht(String header,String payload,Long dvp_real_id)
	{
		this.header=header;
		this.payload=payload;
		this.dvp_real_id=dvp_real_id;
	}

}
