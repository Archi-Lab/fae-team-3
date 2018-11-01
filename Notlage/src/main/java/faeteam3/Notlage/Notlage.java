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
	
	private String datum;
	private boolean behoben=false;
	
	@OneToOne
	@JoinColumn(name = "betroffener_id", referencedColumnName = "id")
	private DVP betroffener;
	
//	@ElementCollection
//	private ArrayList<Nachricht> nachrichten;
	
	@Embedded
	private Benachrichtigung benachrichtigung;
	
	protected Notlage() {}

	public Notlage(String datum, DVP betroffener,Benachrichtigung benachrichtigung) 
	{
		this.datum = datum;
		this.betroffener  = betroffener;
		this.benachrichtigung = benachrichtigung;
		behoben=false;
//		nachrichten = new  ArrayList<Nachricht>();
	}
	
	public Benachrichtigung get_benachrichtigung()
	{
		return benachrichtigung;
	}

	
//	public void add_nachrichtene(Nachricht payload) 
//	{
//		nachrichten.add(payload);
//	}
	
	public boolean ist_behoben()
	{
		return behoben;
	}
	
	public DVP get_betroffener()
	{
		return betroffener;
	}
	
	public void set_behoben()
	{
		behoben =true;
	}
	
	public String  toString()
	{
		return id+" "+behoben+" "+betroffener.toString()+" ";
	}
	
	public Long get_id()
	{
		return id;
	}

	public String get_datum() {
		return datum;
	}

	public DVP get_DVP() {
		return betroffener;
	}

	public void set_wurde_bestätigt(Benachrichtigung bena) 
	{
		benachrichtigung=bena;
		
	}

}
