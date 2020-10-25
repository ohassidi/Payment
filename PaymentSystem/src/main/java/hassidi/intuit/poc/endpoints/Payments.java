/*
 * TestProject
 * 11/18/18
 */
package hassidi.intuit.poc.endpoints;

import java.io.IOException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.couchbase.client.java.document.JsonDocument;
import com.fasterxml.jackson.databind.JsonNode;
import hassidi.intuit.poc.Main;
import hassidi.intuit.poc.common.CBDal;
import hassidi.intuit.poc.common.entities.Payment;
import hassidi.intuit.poc.common.utils.MyObjectMapper;
import hassidi.intuit.poc.entities.PaymentMethods;
import hassidi.intuit.poc.kafka.KafkaProperties;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

/**
 * @author odedh
 */
@Component
@Path("/payments")
public class Payments {

    @GET
    @Path("/{id}/method")
    public Response getPaymentMethod(@PathParam("id") String id) {
        final JsonDocument d = CBDal.getInstance().get(id);
        try {
            final Payment payment = MyObjectMapper.om.readValue(d.content().toString(), Payment.class);
            return Response.ok(payment.getPaymentMethodId()).build();
        } catch (IOException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("pay")
    @Produces({MediaType.APPLICATION_JSON})
    public Response pay (JsonNode body) {
        try {
            final Payment payment = MyObjectMapper.om.treeToValue(body, Payment.class);
            sendPayment2Kafka(payment);

            return Response.accepted(payment).build();
        } catch (IOException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/methods")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getMethods () {
        return Response.ok(PaymentMethods.methodsJson).build();
    }


    private void sendPayment2Kafka (Payment payment) {
        ProducerRecord<String, Payment> record = new ProducerRecord<>(KafkaProperties.TOPIC_NAME, payment.getId(), payment);
        Main.KP.send(record);
    }
}
