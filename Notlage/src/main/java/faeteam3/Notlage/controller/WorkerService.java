package faeteam3.Notlage.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.IllegalActionExcepiton;
import faeteam3.Notlage.kafka.SendeEinheit;
import faeteam3.Notlage.model.Notlage;
import faeteam3.Notlage.repository.NotlageRepository;
import faeteam3.Notlage.model.support.Konstants;


/**
* Basis Controller Klasse für die Bearbeitung von Kafka Events
*/
@Service
public class WorkerService 
{
	private static final Logger LOGGER =
		      LoggerFactory.getLogger(WorkerService.class);
	
    private NotlageRepository notlageRepository;
    
    @Autowired
    private SendeEinheit sender;
    

    @Autowired
	public WorkerService(NotlageRepository notlageRepository)
	{
		this.notlageRepository = notlageRepository;
	}

    /**
    * Das Event von Ungewöhnliche Route wird geparst und eine neue Notlage wird erzeugt.
    * <br> Bei Problemen findet keine Aktion statt.
    * @author FAE: Team 3
    * @version 1.0
    * @param val
    * <br> Die zu parsende Nachricht
    */
	public void bearbeiteMessageUngeRou(String val)
	{
		Notlage neue_nl = new Notlage();
		neue_nl.setOrigin(Konstants.ungeRou_name);
		boolean alles_vorhanden=true;
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(val);
		} catch (IOException e) {
			e.printStackTrace();
			alles_vorhanden=false;
		}
		
		JsonNode node = null;
		
		// TODO teste darauf, ob DVP existiert und ob origin existiert
		// und ob es bereits eine Notlage mit der originID gibt
		
		// TODO  teste, ob die angegebene DVP id doer origin id existiert
		
		// TODO  das Parsen sollte sich auf die echten Daten des ungewöhnliche Route Topics beziehen
		
		node = jsonNode.get("id");
		if (node !=null)
		{
			String field = jsonNode.get("id").asText();
			if (field.compareTo("")!=0)
			{
				neue_nl.setIdOrigin(field);
			}
			else
			{
				alles_vorhanden=false;
			}
		}
		
		node = jsonNode.get("extraInfo");
		if (node !=null)
		{
			String field = jsonNode.get("extraInfo").asText();
			neue_nl.setExtraDatat(field);
		}
		node = jsonNode.get("dvp_id");
		if (node !=null)
		{
			String field = jsonNode.get("dvp_id").asText();
			if (field.compareTo("")!=0)
			{
				neue_nl.setDvp(field);
			}
			else
			{
				alles_vorhanden=false;
			}

		}
		else
			alles_vorhanden=false;
		
		if(alles_vorhanden==true)
		{
			neue_nl = notlageRepository.save(neue_nl);
		    sender.sendNotlage(neue_nl);
		}
		
	}
	
    /**
    * Das Event von Ungewöhnliches Verhalten wird geparst und eine neue Notlage wird erzeugt.
    * <br> Bei Problemen findet keine Aktion statt.
    * @author FAE: Team 3
    * @version 1.0
    * @param val
    * <br> Die zu parsende Nachricht
    */
	public void bearbeiteMessageUngeVer(String val)
	{
		Notlage neue_nl = new Notlage();
		neue_nl.setOrigin(Konstants.ungeVer_name);
		boolean alles_vorhanden=true;
		

		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(val);
		} catch (IOException e) {
			
			e.printStackTrace();
			alles_vorhanden=false;
		}
		
		JsonNode node = null;
		
		// TODO teste darauf, ob DVP existiert und ob origin existiert
		// und ob es bereits eine Notlage mit der originID gibt
		

		// TODO  teste, ob die angegebene DVP id doer origin id existiert
		
		// TODO  das Parsen sollte sich auf die echten Daten des ungewöhnliches Verhalten Topics beziehen
		
		node = jsonNode.get("id");
		if (node !=null)
		{
			String field = jsonNode.get("id").asText();
			if (field.compareTo("")!=0)
			{
				neue_nl.setIdOrigin(field);
			}
			else
			{
				alles_vorhanden=false;
			}

		}
		node = jsonNode.get("extraInfo");
		if (node !=null)
		{
			String field = jsonNode.get("extraInfo").asText();
			neue_nl.setExtraDatat(field);
		}
		node = jsonNode.get("dvp_id");
		if (node !=null)
		{
			String field = jsonNode.get("dvp_id").asText();
			if (field.compareTo("")!=0)
			{
				neue_nl.setDvp(field);
			}
			else
			{
				alles_vorhanden=false;
			}

		}
		else
			alles_vorhanden=false;
		
		if(alles_vorhanden==true)
		{
			neue_nl = notlageRepository.save(neue_nl);
		    sender.sendNotlage(neue_nl);
		}
		
	}
	
}
