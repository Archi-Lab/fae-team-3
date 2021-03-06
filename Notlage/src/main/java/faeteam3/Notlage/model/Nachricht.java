package faeteam3.Notlage.model;

import java.util.UUID;

/**
* Klasse, in der alle Daten enthalten sind, um eine Notlage zu erzeugen
* <br> Diese Klasse wird für die REST Post Funktion benötigt.
*/
public class Nachricht 
{
	private String dvpid;
	private String origin;
	private String origin_id;
	private String payload;

	public Nachricht(){}
	
	public Nachricht(String dvpid,String origin,String origin_id,String payload)
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

	public String getDvpid() {
		return dvpid;
	}

	public void setDvpid(String dvpid) {
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
