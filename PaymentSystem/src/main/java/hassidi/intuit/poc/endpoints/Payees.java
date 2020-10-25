/*
 * TestProject
 * 11/18/18
 */
package hassidi.intuit.poc.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hassidi.intuit.poc.entities.Payee;
import org.springframework.stereotype.Component;

/**
 * @author odedh
 */
@Component
@Path("/payees")
public class Payees {
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response get(@PathParam("id") String id){
        return Response.ok(new Payee("26da3cbe-eb6f-11e8-8eb2-f2801f1b9fd1")).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPayees () {
        return Response.ok("Payees list").build();
    }
}
