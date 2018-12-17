package faeteam3.Bezugsperson.Bezugsperson.models.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Embeddable;

@Embeddable
public class DVP {
	
	private Long internDvpID;

    public DVP(){}

    public DVP(Long id)
    {
        this.internDvpID = id;
    }
    
    public Long getDvpID()
    {
    	return internDvpID;
    }

	
}
