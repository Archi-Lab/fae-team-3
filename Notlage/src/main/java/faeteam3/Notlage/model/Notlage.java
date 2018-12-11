package faeteam3.Notlage.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity(name = "notlage")
public class Notlage  {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
//    @JsonIgnore
    private Long notlageId;

   // @JsonIgnore
    @Embedded
    private DVP dvp;

    @JsonIgnore
    @Embedded
    private Nachricht nachricht;

    private Status status;

//    @JsonIgnore
    @Embedded
    private NotlageBestaetigung bestaetigung = new NotlageBestaetigung();

//    @JsonIgnore
    @Embedded
    private NotlageLoesung loesung = new NotlageLoesung();
    
    public Notlage()
    {

    }
    
    public Notlage(Nachricht nachricht)
    {
    	this.nachricht = nachricht;
        this.dvp=new DVP(nachricht.getDvpid());
        this.status = Status.IN_BEARBEITUNG;
        
    }

    public Long getNotlageId() {
        return notlageId;
    }

    public DVP getDvp() {
        return dvp;
    }

    public Nachricht getNachricht() {
        return nachricht;
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
    
    public void bestaetigeNotlage(Long bpID)
    {
    	bestaetigung.bestaetigen(bpID);
    	markBestaetigt();
    }
    
    public void loeseNotlage(Long bpID)
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

    /**
     * returns {@code true} if {@link Notlage} is in state {@link Status#IN_BEARBEITUNG}, otherwise {@code false}
     * @return {@code true} if {@link Notlage} is in state {@link Status#IN_BEARBEITUNG}, otherwise {@code false}
     */
    
    public boolean isOffen() {
	    return status == Status.IN_BEARBEITUNG;
    }

}
