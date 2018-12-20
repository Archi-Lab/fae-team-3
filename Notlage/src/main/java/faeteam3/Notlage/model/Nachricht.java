package faeteam3.Notlage.model;

import javax.persistence.Embeddable;

public class Nachricht 
{
	private Long dvpid;
	private String origin;
	private  String payload;

	public Nachricht(){}
	
	public Nachricht(String origin, Long dvpid,String payload)
	{
		this.dvpid=dvpid;
		this.setPayload(payload);
		this.origin=origin;
	}

	@Override
	public String toString()
	{
		return payload;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Long getDvpid() {
		return dvpid;
	}

	public void setDvpid(long dvpid) {
		this.dvpid = dvpid;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
