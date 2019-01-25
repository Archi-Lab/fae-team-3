package faeteam3.Notlage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import javax.persistence.*;

@Embeddable
public class NotlageLoesung {

    private Long notlageLoesenId=null;
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

	public Long getNotlageLoesungid() {
		return notlageLoesenId;
	}

	public void setNotlageLoesungid(Long notlageLoesenId) 
	{
		if(this.notlageLoesenId==null)
		{
			this.notlageLoesenId = notlageLoesenId;
		}
	}

	public void loesen(Long bpID) 
	{
		setNotlageLoesungid(bpID);
		setIstGeloest();
		
	}

}
