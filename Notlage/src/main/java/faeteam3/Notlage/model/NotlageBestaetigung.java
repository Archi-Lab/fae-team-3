package faeteam3.Notlage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.UUID;

import javax.persistence.*;

/**
* Embedded Klasse, die festhält, welche BP die Notlage bestätigt hat, und dass die Notlage bestätigt wurde.
*/
@Embeddable
public class NotlageBestaetigung {

    private String bestaetigerBpID=null;
    private boolean istBestaetigt = false;

    public NotlageBestaetigung()
    {
    	
    }

	public boolean isIstBestaetigt() {
		return istBestaetigt;
	}

	public void setIstBestaetigt()
	{
			this.istBestaetigt = true;
	}

	public String getBestaetigerBpID() {
		return bestaetigerBpID;
	}

	public void setBestaetigerBpID(String bestaetigerBpID) 
	{
		if(this.bestaetigerBpID==null)
		{
			this.bestaetigerBpID = bestaetigerBpID;
		}
	}

	public void bestaetigen(String bpID) 
	{
		setBestaetigerBpID(bpID);
		setIstBestaetigt();
	}

}
