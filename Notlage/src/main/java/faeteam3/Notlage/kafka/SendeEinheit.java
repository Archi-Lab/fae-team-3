package faeteam3.Notlage.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import faeteam3.Notlage.model.Notlage;
import faeteam3.Notlage.model.support.UngeRou;
import faeteam3.Notlage.model.support.UngeVer;

/**
 * Sende Einheit
 * <br> Hier befinden sich die Funktionen, um auf Kafka Topics zu veröffentlichen.
 */
@Service
public class SendeEinheit {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SendeEinheit.class);

  @Autowired
  private KafkaTemplate<String, Notlage> kafkaTemplate;
  
  @Autowired
  private KafkaTemplate<String, UngeVer> kafkaTemplate2;
  
  @Autowired
  private KafkaTemplate<String, UngeRou> kafkaTemplate3;
  
 
  
    @Value("${topics.notlage}")
    private String id_notlage_topic;
    @Value("${topics.ungeRou}")
    private String id_UngeVer_topic;
    @Value("${topics.ungeVer}") 
	private String id_UngeRou_topic;
	  
//	public SendeEinheit(
//			@Value("${topics.notlage}")   final String notlage,
//			@Value("${topics.ungeRou}")   final String ungeRou,
//			@Value("${topics.ungeVer}")   final String ungeVer) 
//	{
//		this.id_notlage_topic = notlage;
//		this.id_UngeVer_topic=ungeRou;
//		this.id_UngeRou_topic=ungeVer;
//	}
  

    /**
     * Topic veröffentlicher Funktion auf das Topic Notlage 
     */
    public void sendNotlage(Notlage payload) 
    {
    	LOGGER.info("sending payload='{}'", payload);
   		kafkaTemplate.send(id_notlage_topic, String.valueOf(payload.getNotlageId()),payload);
    }
  
  /**
   * Topic veröffentlicher Funktion auf das Topic UngeVer   
   * <br>  NUR ZUM TESTEN 
   */
  public void sendUngeVer(UngeVer payload) {
	    LOGGER.info("sending payload='{}'", payload);
	    kafkaTemplate2.send(id_UngeVer_topic, String.valueOf(payload.id),payload);
	  }
  
  /**
   * Topic veröffentlicher Funktion auf das Topic UngeRou   
   * <br>  NUR ZUM TESTEN 
   */
  public void sendUngeRou(UngeRou payload) {
	    LOGGER.info("sending payload='{}'", payload);
	    kafkaTemplate3.send(id_UngeRou_topic, String.valueOf(payload.id),payload);
	  }
}

