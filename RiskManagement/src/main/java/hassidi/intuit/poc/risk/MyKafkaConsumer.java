/*
 * poc
 * 11/22/18
 */
package hassidi.intuit.poc.risk;

import java.util.Collections;
import java.util.Properties;

import hassidi.intuit.poc.common.entities.Payment;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;


/**
 * @author odedh
 */
public class MyKafkaConsumer {
    public static Consumer<String, Payment> createConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_BROKERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaProperties.GROUP_ID_CONFIG);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PaymentDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, KafkaProperties.MAX_POLL_RECORDS);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaProperties.OFFSET_RESET_EARLIER);
        Consumer<String, Payment> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(KafkaProperties.TOPIC_NAME));
        return consumer;
    }
}