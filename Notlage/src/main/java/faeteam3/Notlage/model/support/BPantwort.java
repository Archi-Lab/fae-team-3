package faeteam3.Notlage.model.support;

import java.util.UUID;

/**
* Klasse, für REST Put Funktion.
* <br> Die BP id wird heir festgehalten.
*/
public class BPantwort 
{
	private UUID bpID;
	
	public BPantwort()
	{
		
	}

	public UUID getBpID() {
		return bpID;
	}

	public void setBpID(UUID bpID) {
		this.bpID = bpID;
	}

}
