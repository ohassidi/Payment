/*
 * poc
 * 11/25/18
 */
package hassidi.intuit.poc.risk;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.couchbase.client.java.document.Document;
import com.couchbase.client.java.document.RawJsonDocument;
import com.fasterxml.jackson.core.JsonProcessingException;
import hassidi.intuit.poc.common.CBDal;
import hassidi.intuit.poc.common.entities.Payment;
import hassidi.intuit.poc.common.utils.MyObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * @author odedh
 */
public class Main {
    public static final Consumer<String, Payment> KC = MyKafkaConsumer.createConsumer();
    private static final Random R  = new Random();

    public static void main(String[] args) throws Exception {
        runConsumer();
    }

    static void runConsumer() throws InterruptedException {
        int noMessageFound = 0;
        while (true) {
            ConsumerRecords<String, Payment> records = KC.poll(10);
            // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
            if (records.count() == 0) {
                noMessageFound++;
                if (noMessageFound > KafkaProperties.MAX_NO_MESSAGE_FOUND_COUNT)
                    // If no message found count is reached to threshold exit loop.
                    break;
                else {
                    TimeUnit.SECONDS.sleep(10);
                    continue;
                }
            }

            //print each record.
            records.forEach(record -> {
                final Document d = prepare(record.value());
                if (getRisk() > 70) {
                    System.out.println("Too risky, skip");
                } else {
                    if (!d.id().equalsIgnoreCase("FAILED")) CBDal.getInstance().upsert(d);
                }
            });

            // commits the offset of record to broker.
            KC.commitAsync();
        }

        KC.close();
    }

    private static Document prepare (Payment payment) {
        try {
            Payment p = new Payment(
                    payment.getId(),
                    payment.getTimestamp(),
                    payment.getAmount(),
                    payment.getCurrency(),
                    payment.getUserId(),
                    payment.getPayeeId(),
                    payment.getPaymentMethodId(),
                    getRisk());

            return RawJsonDocument.create(p.getId(), MyObjectMapper.om.writeValueAsString(p));
        } catch (JsonProcessingException e) {
            return RawJsonDocument.create("FAILED");
        }
    }

    private static int getRisk() {
        return R.nextInt(100);
    }
}
