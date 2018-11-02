package faeteam3.Notlage;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DVP {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long registerId;
	
	protected DVP() {}
	
	public DVP(Long registerId) 
	{
		this.setRegisterId(registerId);
	}
	
	public Long get_id()
	{
		return id;
	}
	

	public Long getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}
	@Override
	public String toString()
	{
		return " "+id+" "+registerId;
	}
	
	

}
