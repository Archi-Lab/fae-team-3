package faeteam3.Notlage;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("derService")
public class MyServer
{
	
	@Autowired
	private DVPRepository dvp_repository;
	  
	@Autowired
	private NotlageRepository notlage_repository;
	
	public void neue_Person(DVP np)
	{
		
		Optional<DVP> db_person = dvp_repository.findByRegisterId(np.registerId());
		if(!db_person.isPresent())
		{
			final DVP savedPerson1 = this.dvp_repository.save(np);
		}
	}
	
	public void neue_Notlage(Notlage no)
	{
		DVP person = no.get_betroffener();
		
		Optional<DVP> db_person = dvp_repository.findByRegisterId(person.registerId());
		if(db_person.isPresent())
		{
			if(db_person.get().registerId() == person.registerId())
			{
				List<Notlage> reale_not = notlage_repository.findBybetroffener(db_person.get());
				boolean test = false;
				for (Notlage notlage : reale_not) 
				{
					if(notlage.ist_behoben()==false)
					{
						test=true;
						break;
					}
				}
				if(test==false)
				{

				    final Notlage not1 = new Notlage(no.get_datum(),db_person.get());

				    final Notlage saved_not1 = this.notlage_repository.save(not1);
				    sende_nachricht();
				}
			}
		}
		
	}
	
	public void notlage_behoben(DVP np)
	{
		Optional<DVP> db_person = dvp_repository.findByRegisterId(np.registerId());
		if(db_person.isPresent())
		{
			List<Notlage> not = notlage_repository.findBybetroffener(db_person.get());
			Notlage die_Notlage = null;
			boolean test = false;
			for (Notlage notlage : not) 
			{
				if(notlage.ist_behoben()==false)
				{
					die_Notlage=notlage;
					test=true;
					break;
				}
			}
			if(test==true)
			{
				die_Notlage.set_behoben();
				notlage_repository.save(die_Notlage);
			    sende_erfolg_nachricht();
			}
		}
	}
	
	private void sende_nachricht()
	{
		
	}
	
	private void sende_erfolg_nachricht()
	{
		
	}

}
