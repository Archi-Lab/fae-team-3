package faeteam3.Notlage;

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
	
	private String name;
	
	
	protected DVP() {}
	
	public DVP(String tnameype , Long registerId) 
	{
		this.name = name;
		this.registerId = registerId;
	}
	
	public Long get_id()
	{
		return id;
	}
	
	public Long registerId()
	{
		return registerId;
	}
	
	public String  toString()
	{
		return id+" "+name;
	}

	public String getName() {
		return name;
	}

}
