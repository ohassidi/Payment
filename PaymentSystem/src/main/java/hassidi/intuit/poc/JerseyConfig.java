package hassidi.intuit.poc;

import hassidi.intuit.poc.endpoints.Payees;
import hassidi.intuit.poc.endpoints.Payments;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig 
{
    public JerseyConfig() 
    {
        registerClasses(Payments.class, Payees.class);
    }
}
