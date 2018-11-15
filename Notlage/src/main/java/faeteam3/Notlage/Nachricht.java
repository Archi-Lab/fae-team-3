package faeteam3.Notlage;

import javax.persistence.Embeddable;

@Embeddable
public class Nachricht 
{
	private String payload;

	public Nachricht(){}
	
	public Nachricht(String payload)
	{
		this.payload=payload;
	}
	
	public String getPayload()
	{
		return payload;
	}
	
	@Override
	public String  toString()
	{
		return payload;
	}

}
