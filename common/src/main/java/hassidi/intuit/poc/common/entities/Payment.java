/*
 * TestProject
 * 11/18/18
 */
package hassidi.intuit.poc.common.entities;

import java.util.UUID;

/**
 * @author odedh
 */
public class Payment {
    private final double amount;
    private final String currency;
    private final String userId;
    private final String payeeId;
    private final String paymentMethodId;
    private final Integer risk;

    private final String id;
    private final long timestamp;

    // constructor for the json parser to init
    public Payment() {
        this(UUID.randomUUID().toString(), System.currentTimeMillis(), 0, null, null, null, null, null);
    }

    public Payment(String id, long timestamp, double amount, String currency, String userId, String payeeId, String paymentMethodId, Integer risk) {
        this.id = id==null?UUID.randomUUID().toString():id;
        this.timestamp = timestamp==0?System.currentTimeMillis():timestamp;
        this.amount = amount;
        this.currency = currency;
        this.userId = userId;
        this.payeeId = payeeId;
        this.paymentMethodId = paymentMethodId;
        this.risk = risk;
    }

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getUserId() {
        return userId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public Integer getRisk() {
        return risk;
    }
}
