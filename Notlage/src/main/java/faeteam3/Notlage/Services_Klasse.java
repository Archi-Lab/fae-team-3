package faeteam3.Notlage;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("derService")
public class Services_Klasse
{
	
	  
	@Autowired
	private NotlageRepository notlage_repository;
	
	
	/*
	public void nachricht_eingang(Nachricht na)
	{
		Optional<DVP> person = dvp_repository.findByRegisterId(na.dvp_real_id);
		if (person.isPresent())
		{
			DVP pers = person.get();
			Benachrichtigung neue_benach = new Benachrichtigung(pers.getRegisterId(),"infos");
			Notlage notlage_neu= new Notlage("datum",pers,neue_benach);
			notlage_repository.save(notlage_neu);
			
			
			
			sende_nachricht(neue_benach);
			
		}
	}
	
	public void bestätigung_eingang(Long dvp_id)
	{
		Optional<DVP> db_person = dvp_repository.findByRegisterId(dvp_id);
		if (db_person.isPresent())
		{
			DVP pers = db_person.get();
			List<Notlage> no = notlage_repository.findBybetroffener(pers);
			for (Notlage notlage : no) 
			{
				if(notlage.ist_behoben()==false)
				{
					Benachrichtigung bena = notlage.get_benachrichtigung();
					if(bena.isWurde_bestätigt()==false)
					{
						bena.setWurde_bestätigt();
						notlage.set_wurde_bestätigt(bena);
						notlage_repository.save(notlage);
					}
					break;
				}
				
			}
			
		}
	}
	
	public void bestätigung_notlage_behoben_eingang(Long dvp_id)
	{
		Optional<DVP> db_person = dvp_repository.findByRegisterId(dvp_id);
		if (db_person.isPresent())
		{
			DVP pers = db_person.get();
			List<Notlage> no = notlage_repository.findBybetroffener(pers);
			for (Notlage notlage : no) 
			{
				if(notlage.ist_behoben()==false)
				{
					notlage.set_behoben();
					notlage_repository.save(notlage);
					sende_erfolg_nachricht();
					break;
				}
				
			}
			
		}
	}
	
	*/
	
	private void sende_nachricht(Benachrichtigung neue_benach)
	{
		// zu BP
	}
	
	private void sende_erfolg_nachricht()
	{
		// zu  wer die Notlage gemeldet hat
	}



	public Nachricht erstelle_neue_Nachricht(Nachricht na) {
		// TODO Auto-generated method stub
		return  new Nachricht("h2", "payload", 44l);
	}

	public Nachricht findNachricht(long id) {
		// TODO Auto-generated method stub
		return new Nachricht("header", "payload", id);
	}

	public Notlage erstelle_neue_Notlage(Nachricht na) {
		// TODO Auto-generated method stub
		return null;
	}

}
