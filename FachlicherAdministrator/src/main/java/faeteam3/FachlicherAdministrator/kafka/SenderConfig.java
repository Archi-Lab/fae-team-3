package faeteam3.FachlicherAdministrator.kafka;

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
    props.put("client.id", "FA_Client_producer"+id_for_client);

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
  
  // der FA hat bisher keinen Grund, auf ein Topic zu senden
  
  @Bean
  public ProducerFactory<String, Object> producerFactory() {
    return new DefaultKafkaProducerFactory<>(producerConfigs(bootstrapServers,"p1"));
  }

  @Bean
  public KafkaTemplate<String, Object> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }


  @Bean
  public SendeEinheit sender() {
    return new SendeEinheit();
  }
}