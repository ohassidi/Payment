/*
 * TestProject
 * 11/19/18
 */
package hassidi.intuit.poc.entities;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * @author odedh
 */
public enum PaymentMethods {
    CREDIT,
    CASH,
    BANK_TRANSFER;

    public final static String methodsJson =  new ArrayNode(JsonNodeFactory.instance)
            .add(CREDIT.name())
            .add(CASH.name())
            .add(BANK_TRANSFER.name())
            .toString();
}
