package hassidi.intuit.poc;

import hassidi.intuit.poc.common.entities.Payment;
import hassidi.intuit.poc.kafka.MyKafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Main extends SpringBootServletInitializer {
    public static final Producer<String, Payment> KP = MyKafkaProducer.createProducer();

    public static void main(String[] args) {
        new Main().configure(new SpringApplicationBuilder(Main.class)).run(args);
    }
}
