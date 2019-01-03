package faeteam3.Notlage.model;

import javax.persistence.Embeddable;

public class Nachricht 
{
	private Long dvpid;
	private String origin;
	private String origin_id;
	private  String payload;

	public Nachricht(){}
	
	public Nachricht(String origin_id,String origin, Long dvpid,String payload)
	{
		this.dvpid=dvpid;
		this.setPayload(payload);
		this.origin=origin;
		this.setOrigin_id(origin_id);
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

	public String getOrigin_id() {
		return origin_id;
	}

	public void setOrigin_id(String origin_id) {
		this.origin_id = origin_id;
	}

}
