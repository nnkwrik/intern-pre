package echo;



import common.EchoRequest;
import common.EchoResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by nnkwrik
 * 18/08/10 9:06
 */

@Path("/")
@RequestScoped
public class EchoResource {


    @Inject
    private EchoService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{message}")
    public String echoGET(@PathParam("message") String message){

        return message;

    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/")
    public String echoPOST(@FormParam("message") String message){

        return message;

    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON}) //request type
    @Produces({MediaType.APPLICATION_JSON}) //response type
    @Path("/")
    public EchoResponse echoJson(EchoRequest request){

        return service.getEchoResp(request);

    }


}
