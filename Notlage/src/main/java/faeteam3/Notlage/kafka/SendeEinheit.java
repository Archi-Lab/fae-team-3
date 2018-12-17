package faeteam3.Notlage.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import faeteam3.Notlage.model.Notlage;
import faeteam3.Notlage.model.support.UngeRou;
import faeteam3.Notlage.model.support.UngeVer;

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
  

  public void sendNotlage(Notlage payload) {
    LOGGER.info("sending payload='{}'", payload);
    kafkaTemplate.send("Notlage.t", payload);
  }
  
  public void sendUngeVer(UngeVer payload) {
	    LOGGER.info("sending payload='{}'", payload);
	    kafkaTemplate2.send("ungeVer.t", payload);
	  }
  
  public void sendUngeRou(UngeRou payload) {
	    LOGGER.info("sending payload='{}'", payload);
	    kafkaTemplate3.send("ungeRou.t", payload);
	  }
}