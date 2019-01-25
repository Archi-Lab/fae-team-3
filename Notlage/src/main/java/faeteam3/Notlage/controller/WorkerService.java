package faeteam3.Notlage.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import faeteam3.Notlage.kafka.SendeEinheit;
import faeteam3.Notlage.model.Notlage;
import faeteam3.Notlage.repository.NotlageRepository;
import faeteam3.Notlage.model.support.Konstants;


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
			return;
		}
		JsonNode node = null;
		
		node = jsonNode.get("id");
		if (node !=null)
		{
			String field = jsonNode.get("id").asText();
			neue_nl.setIdOrigin(field);
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
			neue_nl.setDvp(Long.parseLong(field));
		}
		else
			alles_vorhanden=false;
		
		if(alles_vorhanden==true)
		{
			neue_nl = notlageRepository.save(neue_nl);
		    sender.sendNotlage(neue_nl);
		}
		
	}
	
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
		}
		JsonNode node = null;
		
		node = jsonNode.get("id");
		if (node !=null)
		{
			String field = jsonNode.get("id").asText();
			neue_nl.setIdOrigin(field);
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
			neue_nl.setDvp(Long.parseLong(field));
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
