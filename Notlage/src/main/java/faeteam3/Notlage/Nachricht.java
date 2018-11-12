package faeteam3.Notlage;

import javax.persistence.Embeddable;

@Embeddable
public class Nachricht 
{
	
	private String header;
	private String payload;
	
	
	public Nachricht(String header,String payload,Long dvp_id)
	{
		this.header=header;
		this.payload=payload;
	}
	
	public String getHeader()
	{
		return header;
	}
	
	public String getPayload()
	{
		return payload;
	}
	
	@Override
	public String  toString()
	{
		return header+" "+payload;
	}

}
