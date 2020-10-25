/*
 * poc
 * 11/22/18
 */
package hassidi.intuit.poc.kafka;

import java.util.Map;

import hassidi.intuit.poc.common.entities.Payment;
import hassidi.intuit.poc.common.utils.MyObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author odedh
 */
public class PaymentSerializer implements Serializer<Payment> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Payment data) {
        byte[] retVal = null;
        try {
            retVal = MyObjectMapper.om.writeValueAsString(data).getBytes();
        } catch (Exception exception) {
            System.out.println("Error in serializing object" + data);
        }
        return retVal;
    }

    @Override
    public void close() {
    }
}
