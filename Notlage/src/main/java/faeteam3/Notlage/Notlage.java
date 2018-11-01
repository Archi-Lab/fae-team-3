package faeteam3.Notlage;


import java.util.ArrayList;

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
	
	private ArrayList<String> payload_meldungen;
	
	protected Notlage() {}

	public Notlage(String datum, DVP betroffener) 
	{
		this.datum = datum;
		this.betroffener  = betroffener;
		behoben=false;
		payload_meldungen = new  ArrayList<String>();
	}

	
	public void add_payload_message(String payload) 
	{
		payload_meldungen.add(payload);
	}
	
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

}
