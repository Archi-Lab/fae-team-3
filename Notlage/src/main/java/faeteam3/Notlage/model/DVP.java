package faeteam3.Notlage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Embeddable;
import java.util.UUID;

/**
* Embedded Klasse, in der die DVP id enthalten ist
*/
@Embeddable
public class DVP {
	
	private UUID internDvpID;
	

    public DVP(){}

    public DVP(UUID id)
    {
        this.internDvpID = id;
    }
    
    public UUID getDvpID()
    {
    	return internDvpID;
    }

	
}
