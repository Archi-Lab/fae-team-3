package faeteam3.Notlage.model;


import java.util.UUID;

import javax.persistence.*;

/**
* Klasse, welche die Notlage angibt.
*/
@Entity(name = "notlage")
public class Notlage  {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long notlageId;

    @Embedded
    private DVP dvp;

    @Embedded
    private ExtraData data = new ExtraData("");

    private Status status;


    @Embedded
    private NotlageBestaetigung bestaetigung = new NotlageBestaetigung();
    
    private String origin;
    
    private UUID idOrigin;


    @Embedded
    private NotlageLoesung loesung = new NotlageLoesung();
    
    public Notlage()
    {
    	this.status = Status.IN_BEARBEITUNG;
    }
    
    public DVP getDvp() {
        return dvp;
    }
    
    public void setDvp(UUID id) {
    	dvp= new DVP(id);
    }

    
    /**
     */
    public ExtraData getExtraData() {
        return data;
    }
    
    public void setExtraDatat(String data) {
    	this.data.setData(data);
    }

    public Status getStatus() {
        return status;
    }

    public boolean isBestaetigt() 
    {
	    return bestaetigung.isIstBestaetigt();
    }

    public boolean isGeloest() {
        return loesung.isIstGeloest();
    }
    
    public NotlageBestaetigung getNotlageBestaetigung()
    {
    	return bestaetigung;
    }
    
    public void bestaetigeNotlage(UUID bpID)
    {
    	bestaetigung.bestaetigen(bpID);
    	markBestaetigt();
    }
    
    public void loeseNotlage(UUID bpID)
    {
    	loesung.loesen(bpID);
    	 markGeloest();
    }
    
    public NotlageLoesung getNotlageLoesung()
    {
    	return loesung;
    }

    private void markBestaetigt() {
        if(status == Status.GELOEST || status == Status.BESTÄTIGT )
        {
            return;
        }
        else 
        {
        	status = Status.BESTÄTIGT;
        }
    }

    private void markGeloest() {
        if(status == Status.IN_BEARBEITUNG)
        {
            throw new IllegalStateException("Notlage wasnt confirmed!");
        }
        else if(status == Status.GELOEST)
        {
            return;
        }
        else
        {
        	status = Status.GELOEST;
        }     
    }


    public boolean isOffen() {
	    return status == Status.IN_BEARBEITUNG;
    }


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public UUID getIdOrigin() {
		return idOrigin;
	}


	public void setIdOrigin(UUID idOrigin) {
		this.idOrigin = idOrigin;
	}



	public Long getNotlageId() {
		return notlageId;
	}



	public void setNotlageId(Long notlageId) {
		this.notlageId = notlageId;
	}

}
