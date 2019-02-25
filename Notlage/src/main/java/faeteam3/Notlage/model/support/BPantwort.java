package faeteam3.Notlage.model.support;

import java.util.UUID;

/**
* Klasse, für REST Put Funktion.
* <br> Die BP id wird heir festgehalten.
*/
public class BPantwort 
{
	private String bpID;
	
	public BPantwort()
	{
		
	}

	public String getBpID() {
		return bpID;
	}

	public void setBpID(String bpID) {
		this.bpID = bpID;
	}

}
