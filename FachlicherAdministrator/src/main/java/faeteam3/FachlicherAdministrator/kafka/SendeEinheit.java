package faeteam3.FachlicherAdministrator.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class SendeEinheit {
	
  private static final Logger LOGGER =
      LoggerFactory.getLogger(SendeEinheit.class);

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;
  

  public void send(String topic,Object payload) {
    LOGGER.info("sending payload='{}'", payload);
    kafkaTemplate.send(topic, payload);
  }
  
}