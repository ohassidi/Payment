/*
 * TestProject
 * 11/22/18
 */
package hassidi.intuit.poc.kafka;

import java.util.Properties;

import hassidi.intuit.poc.common.entities.Payment;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author odedh
 */
public class MyKafkaProducer {
    public static Producer<String, Payment> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaProperties.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // the payment id
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PaymentSerializer.class.getName()); // payment body?
        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class.getName());
        return new KafkaProducer<>(props);
    }
}
