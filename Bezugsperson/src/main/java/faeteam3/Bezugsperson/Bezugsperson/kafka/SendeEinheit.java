package faeteam3.Bezugsperson.Bezugsperson.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import faeteam3.Bezugsperson.Bezugsperson.models.Bezugsperson;

@Service
public class SendeEinheit {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SendeEinheit.class);

  @Autowired
  private KafkaTemplate<String, Bezugsperson> kafkaTemplate;

    @Value("${topics.bpmeta}") 
	private String id_bp_meta_topic;
	  
    
  public void sendBPMeta(Bezugsperson payload) {
    LOGGER.info("sending payload='{}'", payload);
    kafkaTemplate.send(id_bp_meta_topic, payload);
  }
  

}