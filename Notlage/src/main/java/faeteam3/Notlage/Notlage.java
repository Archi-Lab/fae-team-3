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
	
	@Embedded
	private DVP dvp;
	@Embedded
	private Nachricht nachricht;
	private boolean bestätigt=false;
	private boolean gelöst = false;
	
	@Embedded
	private Bestätiger bestätiger;
	@Embedded
	private Löser löser ;
	
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

	public Notlage(Nachricht nachricht) 
	{
		dvp = new DVP(nachricht.getDvpid());
		this.nachricht=nachricht;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public DVP getDVP()
	{
		return dvp;
	}
	
	public Nachricht getNachricht()
	{
		return nachricht;
	}
	
	public boolean getBestätigt()
	{
		return bestätigt;
	}
	
	public boolean getGelöstt()
	{
		return gelöst;
	}
	
	public Bestätiger getBestätiger()
	{
		return bestätiger;
	}
	
	public Löser getLöser()
	{
		return löser;
	}
	
	public void setBestätigt(Long uuid)
	{
		bestätigt=true;
		bestätiger= new Bestätiger(uuid);
	}
	
	public void setGelöst(Long uuid)
	{
		gelöst=true;
		löser= new Löser(uuid);
	}
	
	public String  toString()
	{
		return id+" ";
	}
	
	public Long get_id()
	{
		return id;
	}
	

}
