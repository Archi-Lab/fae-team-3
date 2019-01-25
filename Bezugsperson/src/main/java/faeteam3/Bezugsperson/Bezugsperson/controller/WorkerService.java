package faeteam3.Bezugsperson.Bezugsperson.controller;

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

import faeteam3.Bezugsperson.Bezugsperson.kafka.SendeEinheit;


@Service
public class WorkerService 
{
	private static final Logger LOGGER =
		      LoggerFactory.getLogger(WorkerService.class);
	

    @Autowired
    private SendeEinheit sender;
    

	public WorkerService()
	{
		
	}

	public void bearbeiteMessageNotlage(String val)
	{
	
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
        		;
        	}
        	else if(key == "data2")
        	{
        		; // vlt. in notlage speichern origin of notlage mit type
        	}
        	else if(key == "dvp_id")
        	{
        		;
        	}
        	
        }
 
//        sender.sendBPMeta(neue_nl);
	}
	
	public void bearbeiteMessageBPCore(String val)
	{

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
        		;
        	}
        	else if(key == "data2")
        	{
        		; // vlt. in notlage speichern origin of notlage mit type
        	}
        	else if(key == "dvp_id")
        	{
        		;
        	}
        	
        }

//        sender.sendBPMeta(neue_nl);
	}
	

}
