package faeteam3.Notlage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import java.util.UUID;

/**
* Embedded Klasse, in der die DVP id enthalten ist
*/
@Embeddable
public class DVP {
	
	
	private String internDvpID;
	

    public DVP(){}

    public DVP(String id)
    {
        this.internDvpID = id;
    }
    
    public String getDvpID()
    {
    	return internDvpID;
    }

	
}
