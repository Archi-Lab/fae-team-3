package faeteam3.Bezugsperson.Bezugsperson.models.support;

import javax.persistence.Embeddable;


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
