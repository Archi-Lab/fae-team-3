package faeteam3.Bezugsperson.Bezugsperson.kafka;

import java.util.concurrent.CountDownLatch;
import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import faeteam3.Bezugsperson.Bezugsperson.controller.WorkerService;
import faeteam3.Bezugsperson.Bezugsperson.models.support.BPCore;
import faeteam3.Bezugsperson.Bezugsperson.models.support.Notlage;

import org.apache.kafka.common.header.Headers;


public class ReceiverEinheit {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ReceiverEinheit.class);

  private CountDownLatch latch = new CountDownLatch(1);
  
  @Autowired
  private WorkerService service;

  public CountDownLatch getLatch() {
    return latch;
  }
  
  //Kafka hört auf 2 Topics  Namen der Topocs müssen 
	
  

  @KafkaListener(topics = "BPCore.t" , containerFactory = "kafkaListenerContainerFactory1") //  clientIdPrefix = "json",
  public void receive_topic_1(ConsumerRecord<String, BPCore> cr ) {
    
    
    LOGGER.info("Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
    		typeIdHeader(cr.headers()), cr.value(), cr.toString());
    // TODO update BP
    latch.countDown();
  }
  
  @KafkaListener(topics = "Notlage.t" ,  containerFactory = "kafkaListenerContainerFactory2") //  clientIdPrefix = "json",
  public void receive_topic_2(ConsumerRecord<String, Notlage> cr) {
    
    LOGGER.info("Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
    		typeIdHeader(cr.headers()), cr.value(), cr.toString());
    service.analyseNotlage(cr.value());
    latch.countDown();
  }
  
  
  private static String typeIdHeader(Headers headers) {
      return StreamSupport.stream(headers.spliterator(), false)
              .filter(header -> header.key().equals("__TypeId__"))
              .findFirst().map(header -> new String(header.value())).orElse("N/A");
  }
  
  
}



