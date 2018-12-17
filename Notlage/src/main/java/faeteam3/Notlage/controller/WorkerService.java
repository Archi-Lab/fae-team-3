package faeteam3.Notlage.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import faeteam3.Notlage.kafka.ReceiverEinheit;
import faeteam3.Notlage.kafka.SendeEinheit;
import faeteam3.Notlage.model.Notlage;
import faeteam3.Notlage.model.support.UngeRou;
import faeteam3.Notlage.repository.NotlageRepository;

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

		ObjectMapper  objectMapper = new ObjectMapper();
        Map<String, String> empMap = null;
		try {
			empMap = objectMapper.readValue(val,Map.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        for (Map.Entry<String, String> entry : empMap.entrySet())
        {
        	String key = entry.getKey();
        	String value = entry.getValue();
        	LOGGER.info("Key: {}  Value {} ",key, value);
        	if(key == "id")
        	{
        		; // vlt. in notlage speichern origin of notlage mit type
        	}
        	else if(key == "extraInfo")
        	{
        		neue_nl.setExtraDatat(value);
        	}
        	else if(key == "data2")
        	{
        		; // vlt. in notlage speichern origin of notlage mit type
        	}
        	else if(key == "dvp_id")
        	{
        		neue_nl.setDvp(Long.parseLong(value));
        	}
        	
        }
        	
        neue_nl = notlageRepository.save(neue_nl);
        
        sender.sendNotlage(neue_nl);
	}
	
	public void bearbeiteMessageUngeVer(String val)
	{
		Notlage neue_nl = new Notlage();

		ObjectMapper  objectMapper = new ObjectMapper();
        Map<String, String> empMap = null;
		try {
			empMap = objectMapper.readValue(val,Map.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        for (Map.Entry<String, String> entry : empMap.entrySet())
        {
        	String key = entry.getKey();
        	String value = entry.getValue();
        	LOGGER.info("Key: {}  Value {} ",key, value);
        	if(key == "id")
        	{
        		; // vlt. in notlage speichern origin of notlage mit type
        	}
        	else if(key == "extraInfo")
        	{
        		neue_nl.setExtraDatat(value);
        	}
        	else if(key == "data2")
        	{
        		; // vlt. in notlage speichern origin of notlage mit type
        	}
        	else if(key == "dvp_id")
        	{
        		neue_nl.setDvp(Long.parseLong(value));
        	}
        	
        }
        	
        neue_nl = notlageRepository.save(neue_nl);
        
        sender.sendNotlage(neue_nl);
	}
	
	
	public void bearbeiteMessageUngeRou2(Object obj)
	{
		long ungeRou_id=(Long) null;
		String ungeRou_data1=null;
		String ungeRou_data2=null;
		long ungeRou_dvp_id=(Long) null;
		
		try
		{
			Field f = obj.getClass().getDeclaredField("id"); //NoSuchFieldException
			f.setAccessible(true);
			ungeRou_id = (long) f.get(obj); //IllegalAccessException
		}
		catch(NoSuchFieldException | IllegalAccessException e )
		{
			LOGGER.info(e.toString());
		}
		
		try
		{
			Field f = obj.getClass().getDeclaredField("data1"); 
			f.setAccessible(true);
			ungeRou_data1 = (String) f.get(obj); 
		}
		catch(NoSuchFieldException | IllegalAccessException e )
		{
			LOGGER.info(e.toString());
		}
		
		try
		{
			Field f = obj.getClass().getDeclaredField("data1"); 
			f.setAccessible(true);
			ungeRou_data2 = (String) f.get(obj); 
		}
		catch(NoSuchFieldException | IllegalAccessException e )
		{
			LOGGER.info(e.toString());
		}
		
		try
		{
			Field f = obj.getClass().getDeclaredField("dvp_id"); 
			f.setAccessible(true);
			ungeRou_dvp_id = (long) f.get(obj); 
		}
		catch(NoSuchFieldException | IllegalAccessException e )
		{
			LOGGER.info(e.toString());
		}

		
		LOGGER.info("Parsed Data UngeRou {}  {} {} {} ",ungeRou_id ,ungeRou_data1,ungeRou_data2,ungeRou_dvp_id);
	}
	
	public void bearbeiteMessageUngeVer2(Object obj)
	{
		long ungeRou_id=(Long) null;
		String ungeRou_data1=null;
		String ungeRou_data2=null;
		long ungeRou_dvp_id=(Long) null;
		
		try
		{
			Field f = obj.getClass().getDeclaredField("id"); //NoSuchFieldException
			f.setAccessible(true);
			ungeRou_id = (long) f.get(obj); //IllegalAccessException
		}
		catch(NoSuchFieldException | IllegalAccessException e )
		{
			LOGGER.info(e.toString());
		}
		
		try
		{
			Field f = obj.getClass().getDeclaredField("data1"); 
			f.setAccessible(true);
			ungeRou_data1 = (String) f.get(obj); 
		}
		catch(NoSuchFieldException | IllegalAccessException e )
		{
			LOGGER.info(e.toString());
		}
		
		try
		{
			Field f = obj.getClass().getDeclaredField("data1"); 
			f.setAccessible(true);
			ungeRou_data2 = (String) f.get(obj); 
		}
		catch(NoSuchFieldException | IllegalAccessException e )
		{
			LOGGER.info(e.toString());
		}
		
		try
		{
			Field f = obj.getClass().getDeclaredField("dvp_id"); 
			f.setAccessible(true);
			ungeRou_dvp_id = (long) f.get(obj); 
		}
		catch(NoSuchFieldException | IllegalAccessException e )
		{
			LOGGER.info(e.toString());
		}

		
		LOGGER.info("Parsed Data UngeVer {}  {} {} {} ",ungeRou_id ,ungeRou_data1,ungeRou_data2,ungeRou_dvp_id);
	}

}
