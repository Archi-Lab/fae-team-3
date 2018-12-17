package faeteam3.FachlicherAdministrator.kafka;

import java.util.concurrent.CountDownLatch;
import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.apache.kafka.common.header.Headers;

import faeteam3.FachlicherAdministrator.Controller.WorkerService;
import faeteam3.FachlicherAdministrator.models.support.Bezugsperson;

public class ReceiverEinheit {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ReceiverEinheit.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }
  
  @Autowired
  WorkerService service;
  

  @KafkaListener(topics = "BPMeta.t" , containerFactory = "kafkaListenerContainerFactory1") //  clientIdPrefix = "json",
  public void receive_topic_1(ConsumerRecord<String, Bezugsperson> cr ) {
    
    
    LOGGER.info("Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
    		typeIdHeader(cr.headers()), cr.value(), cr.toString());
    service.analyseBPMeta(cr.value());
    latch.countDown();
  }

  
  private static String typeIdHeader(Headers headers) {
      return StreamSupport.stream(headers.spliterator(), false)
              .filter(header -> header.key().equals("__TypeId__"))
              .findFirst().map(header -> new String(header.value())).orElse("N/A");
  }
  
  
}

