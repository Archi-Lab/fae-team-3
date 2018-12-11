package faeteam3.Notlage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import javax.persistence.*;

@Embeddable
public class NotlageBestaetigung {

    private Long notlageBestaetigungId=null;
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

	public Long getNotlageBestaetigungid() {
		return notlageBestaetigungId;
	}

	public void setNotlageBestaetigungid(Long notlageBestaetigungId) 
	{
		if(this.notlageBestaetigungId==null)
		{
			this.notlageBestaetigungId = notlageBestaetigungId;
		}
	}

	public void bestaetigen(Long bpID) 
	{
		setNotlageBestaetigungid(bpID);
		setIstBestaetigt();
	}

}
