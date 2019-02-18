package faeteam3.Notlage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.UUID;

import javax.persistence.*;

/**
* Embedded Klasse, die festhält, welche BP die Notlage gelöst hat, und dass die Notlage gelöst wurde.
*/
@Embeddable
public class NotlageLoesung {

    private UUID loeserBpID=null;
    private boolean istGeloest=false;


    public NotlageLoesung()
    {
    	
    }
    
	public boolean isIstGeloest() {
		return istGeloest;
	}


	public void setIstGeloest() 
	{
			this.istGeloest = true;
	}

	public UUID getLoeserBpID() {
		return loeserBpID;
	}

	public void setLoeserBpID(UUID loeserBpID) 
	{
		if(this.loeserBpID==null)
		{
			this.loeserBpID = loeserBpID;
		}
	}

	public void loesen(UUID bpID) 
	{
		setLoeserBpID(bpID);
		setIstGeloest();
		
	}

}
