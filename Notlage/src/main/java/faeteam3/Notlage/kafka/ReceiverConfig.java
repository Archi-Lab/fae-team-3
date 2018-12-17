package faeteam3.Notlage.kafka;

import faeteam3.Notlage.model.support.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafka
public class ReceiverConfig {

//	 @Value("${spring.kafka.consumer.bootstrap-servers}")
	// 192.168.56.101:9092  = "kafka:9092"
  private final String bootstrapServers ;

//  @Bean
  public Map<String, Object> consumerConfigs(String bs, String id_for_client) {
    Map<String, Object> props = new HashMap<>();
    // list of host:port pairs used for establishing the initial connections to the Kafka cluster
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bs);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
    		StringDeserializer.class);
    // allows a pool of processes to divide the work of consuming and processing records
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_name");
    // automatically reset the offset to the earliest offset
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put("client.id", "Notlage_Client_consumer"+id_for_client);

    return props;
  }
  


  
  public ReceiverConfig(@Value("${eventing.brokers}") final String servers) {
	    this.bootstrapServers = servers;
//	    log.info(servers);
	}
  
  @Bean
  public ConsumerFactory<String, Object> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(bootstrapServers,"c1"));
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());

    return factory;
  }
  
  @Bean
  public ConsumerFactory<String, String> consumerFactoryY1() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(bootstrapServers,"y1"), new StringDeserializer(), new StringDeserializer());
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactoryY1() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactoryY1());

    return factory;
  }
  
  @Bean
  public ConsumerFactory<String, String> consumerFactoryY2() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(bootstrapServers,"y2"), new StringDeserializer(), new StringDeserializer());
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactoryY2() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactoryY2());

    return factory;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  @Bean
  public ConsumerFactory<String, Object> consumerFactoryZ1() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(bootstrapServers,"z1"), new StringDeserializer(), new JsonDeserializer(Object.class));
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactoryZ1() {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactoryZ1());

    return factory;
  }
  
  @Bean
  public ConsumerFactory<String, Object> consumerFactoryZ2() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(bootstrapServers,"z2"), new StringDeserializer(), new JsonDeserializer(Object.class));
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactoryZ2() {
    ConcurrentKafkaListenerContainerFactory<String, Object> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactoryZ2());

    return factory;
  }
  

 

  @Bean
  public ConsumerFactory<String, UngeRou> consumerFactory1() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(bootstrapServers,"c2"), new StringDeserializer(), new JsonDeserializer(UngeRou.class));
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, UngeRou>> kafkaListenerContainerFactory1() {
    ConcurrentKafkaListenerContainerFactory<String, UngeRou> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory1());

    return factory;
  }
  
  
  
  
  
  @Bean
  public ConsumerFactory<String, UngeVer> consumerFactory2() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(bootstrapServers,"c3"), new StringDeserializer(), new JsonDeserializer(UngeVer.class));
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, UngeVer>> kafkaListenerContainerFactory2() {
    ConcurrentKafkaListenerContainerFactory<String, UngeVer> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory2());

    return factory;
  }
  

  @Bean
  public ReceiverEinheit receiver() {
    return new ReceiverEinheit();
  }
}