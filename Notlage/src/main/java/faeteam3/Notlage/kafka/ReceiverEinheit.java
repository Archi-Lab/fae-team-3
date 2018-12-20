package faeteam3.Notlage.kafka;

import java.util.concurrent.CountDownLatch;
import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.apache.kafka.common.header.Headers;

import faeteam3.Notlage.controller.WorkerService;
import faeteam3.Notlage.model.support.UngeRou;
import faeteam3.Notlage.model.support.UngeVer;

public class ReceiverEinheit {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ReceiverEinheit.class);

  private CountDownLatch latch = new CountDownLatch(1);

  public CountDownLatch getLatch() {
    return latch;
  }
  
//  private final String id_notlage_topic;
//  private final String id_UngeVer_topic;
//  private final String id_UngeRou_topic;
//	  
//	public ReceiverEinheit(
//			@Value("${topics.notlage}")   final String notlage,
//			@Value("${topics.ungeRou}")   final String ungeRou,
//			@Value("${topics.ungeVer}")   final String ungeVer) 
//	{
//		this.id_notlage_topic = notlage;
//		this.id_UngeVer_topic=ungeRou;
//		this.id_UngeRou_topic=ungeVer;
//	}
  
  
  
  @Autowired
  private WorkerService serivce;
  
  @KafkaListener(topics = "${topics.ungeRou}" , containerFactory = "kafkaListenerContainerFactoryY1") //  clientIdPrefix = "json",
  public void receive_topic_n1(ConsumerRecord<String, String> cr ) {
    
    LOGGER.info("Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
    		typeIdHeader(cr.headers()), cr.value(), cr.toString());
    serivce.bearbeiteMessageUngeVer(cr.value());
    latch.countDown();
  }
  
  @KafkaListener(topics = "${topics.ungeVer}" , containerFactory = "kafkaListenerContainerFactoryY2") //  clientIdPrefix = "json",
  public void receive_topic_n2(ConsumerRecord<String, String> cr ) {
    
    
    LOGGER.info("Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
    		typeIdHeader(cr.headers()), cr.value(), cr.toString());
    serivce.bearbeiteMessageUngeRou(cr.value());
    latch.countDown();
  }
  
  private static String typeIdHeader(Headers headers) {
      return StreamSupport.stream(headers.spliterator(), false)
              .filter(header -> header.key().equals("__TypeId__"))
              .findFirst().map(header -> new String(header.value())).orElse("N/A");
}
  
  // das ist sinnvoll, um direkt die sachen aus der properties datei auszulesen.
//  @KafkaListener(groupId = "${my.kafka.conf.groupId}", topics = "#{'${my.kafka.conf.topics}'.split(',')}")
//  public void storeTopicsDataToMongo(
//          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//          @Header(required = false, name= KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
//          @Payload(required = false) String record)
//  {
//      log.trace(format("Received topic[%s] key[%s] payload[%s]", topic, key, record));
//      //your code
//  }
  
}



