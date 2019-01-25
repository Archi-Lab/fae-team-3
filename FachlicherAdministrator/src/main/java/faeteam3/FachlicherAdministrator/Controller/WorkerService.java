package faeteam3.FachlicherAdministrator.Controller;

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

@Service
public class WorkerService 
{
	private static final Logger LOGGER =
		      LoggerFactory.getLogger(WorkerService.class);

	public WorkerService()
	{
    	
	}

	public void bearbeiteMessageBPMetar(String val)
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
        		; // use value
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

	}

}
