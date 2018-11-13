package faeteam3.Notlage;

import javax.persistence.Embeddable;

@Embeddable
public class Nachricht 
{
	
	private String header;
	private String payload;
	private Long dvpid;
	
	protected Nachricht()
	{
		
	}
	
	
	public Nachricht(String header,String payload,Long dvpid)
	{
		this.header=header;
		this.payload=payload;
		this.dvpid=dvpid;
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

	public Long getDvpid() {
		return dvpid;
	}

	public void setDvpid(Long dvpid) {
		this.dvpid = dvpid;
	}

}
