package faeteam3.Bezugsperson.Bezugsperson.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import faeteam3.Bezugsperson.Bezugsperson.models.Bezugsperson;


@Service
public class SendeEinheit {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(SendeEinheit.class);

  @Autowired
  private KafkaTemplate<String, Bezugsperson> kafkaTemplate;

  // Topik name ist:  BPMeta.t
  // könnte man auch direkt hier fest reinschreiben anstatt es als Argument zu machen
  public void send(String topic,Bezugsperson payload) {
    LOGGER.info("sending payload='{}'", payload);
    // sende update von BP auf Topic
    kafkaTemplate.send(topic, payload);
  }
  
}