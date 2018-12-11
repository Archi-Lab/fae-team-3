package faeteam3.Notlage.model;

import javax.persistence.Embeddable;

@Embeddable
public class Nachricht 
{
	private  Long dvpid;
	private  String payload;

	public Nachricht(){}
	
	public Nachricht(String payload,Long dvpid)
	{
		this.payload=payload;
		this.dvpid=dvpid;
	}
	
	public Long getDvpid() {
		return dvpid;
	}
	
	public String getPayload()
	{
		return payload;
	}
	
	@Override
	public String toString()
	{
		return payload;
	}

}
