package faeteam3.Notlage;


import java.util.ArrayList;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Notlage {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private DVP dvp;
	private Nachricht nachricht;
	private int notlageStatus=1;
	
//	
//	@OneToOne
//	@JoinColumn(name = "betroffener_id", referencedColumnName = "id")
//	private Long dvpid;
	
//	@ElementCollection
//	private ArrayList<Nachricht> nachrichten;
	
//	@OneToOne
//	@JoinColumn(name = "id")//, referencedColumnName = "id")
//	private Benachrichtigung benachrichtigung;
	
	protected Notlage() {}

	public Notlage(String datum, Long dvpid,Benachrichtigung benachrichtigung) 
	{
		;
	}
	
//	public Benachrichtigung get_benachrichtigung()
//	{
//		return benachrichtigung;
//	}
//
//
//	public Long get_dvpid()
//	{
//		return dvpid;
//	}
	
	public String  toString()
	{
		return id+" ";
	}
	
	public Long get_id()
	{
		return id;
	}

	
	public void setStatus(int s)
	{
		notlageStatus=s;
	}
	
	public int getStatus()
	{
		return notlageStatus;
	}


}
