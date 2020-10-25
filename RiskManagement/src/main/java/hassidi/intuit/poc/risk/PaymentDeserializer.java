/*
 * poc
 * 11/22/18
 */
package hassidi.intuit.poc.risk;

import java.util.Map;

import hassidi.intuit.poc.common.entities.Payment;
import hassidi.intuit.poc.common.utils.MyObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author odedh
 */
public class PaymentDeserializer implements Deserializer<Payment> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Payment deserialize(String topic, byte[] data) {
        Payment object = null;
        try {
            object = MyObjectMapper.om.readValue(data, Payment.class);
        } catch (Exception exception) {
            System.out.println("Error in deserializing bytes " + exception);
        }
        return object;
    }

    @Override
    public void close() {
    }
}
