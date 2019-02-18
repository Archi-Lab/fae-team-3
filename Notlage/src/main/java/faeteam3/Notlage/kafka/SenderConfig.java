package faeteam3.Notlage.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import faeteam3.Notlage.model.Notlage;
import faeteam3.Notlage.model.support.UngeRou;
import faeteam3.Notlage.model.support.UngeVer;


/**
* Sender Konfiguration für Kafka
*/
@Configuration
public class SenderConfig {

//  @Value("${spring.kafka.consumer.bootstrap-servers}")
	// 192.168.56.101:9092   ="kafka:9092"
	
  private final String bootstrapServers;

//  @Bean
  public Map<String, Object> producerConfigs(String bs,String id_for_client) {
    Map<String, Object> props = new HashMap<>();
    // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
    		bs);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
    		JsonSerializer.class);
//    props.put("client.id", "Notlage_Client_producer"+id_for_client);

    return props;
  }
  

  private static final Logger LOGGER =
	      LoggerFactory.getLogger(SendeEinheit.class);
  
 // @PropertySource(value = "classpath:application.properties")
  public SenderConfig(@Value("${eventing.brokers}") final String servers) {

	  LOGGER.info(servers);

	    this.bootstrapServers = servers;
//	    log.info(servers);
	}
  
  @Bean
  public ProducerFactory<String, Object> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs(bootstrapServers,"p1"));
  }

  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public ProducerFactory<String, Notlage> producerFactoryNotlage() {
    return new DefaultKafkaProducerFactory<>(producerConfigs(bootstrapServers,"p2"));
  }

  @Bean
  public KafkaTemplate<String, Notlage> kafkaTemplateNotlage() {
    return new KafkaTemplate<>(producerFactoryNotlage());
  }
  
  
  @Bean
  public ProducerFactory<String, UngeVer> producerFactoryNotlage2() {
    return new DefaultKafkaProducerFactory<>(producerConfigs(bootstrapServers,"p3"));
  }

  @Bean
  public KafkaTemplate<String, UngeVer> kafkaTemplateNotlage2() {
    return new KafkaTemplate<>(producerFactoryNotlage2());
  }
  
  
  @Bean
  public ProducerFactory<String, UngeRou> producerFactoryNotlage3() {
    return new DefaultKafkaProducerFactory<>(producerConfigs(bootstrapServers,"p4"));
  }

  @Bean
  public KafkaTemplate<String, UngeRou> kafkaTemplateNotlage3() {
    return new KafkaTemplate<>(producerFactoryNotlage3());
  }

  @Bean
  public SendeEinheit sender() {
    return new SendeEinheit();
  }
}