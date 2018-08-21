package proxy;

import common.EchoRequest;
import common.EchoResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

@Path("/")
@RequestScoped
public class ProxyResource {


    @Inject
    @ConfigProperty(name = "echo-ip", defaultValue = "localhost")
    private String echoIp;

    @Inject
    @ConfigProperty(name = "echo-port", defaultValue = "8080")
    private String echoPort;


    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/")
    public String test() {
        return "proxy server is working, echo-server( " + echoIp + ":" + echoPort + " )";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/echo/{message}")
    public String echoGET(@PathParam("message") String message, @Context HttpServletRequest request) {

        String url = "http://" + echoIp + ":" + echoPort + "/" + message;
        System.out.println("from " + request.getRemoteAddr() + " to echo server (GET " + url + ")");
        Request apiRequest = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            Response apiResponse = okHttpClient.newCall(apiRequest).execute();
            return apiResponse.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "500";
        }


    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/echo")
    public EchoResponse echoPOST(@FormParam("message") String message, @Context HttpServletRequest request) {
        String url = "http://" + echoIp + ":" + echoPort;
        System.out.println("from " + request.getRemoteAddr() + " to echo server (POST " + url + ")");

        EchoRequest echoRequest = new EchoRequest();

        echoRequest.setIp(request.getRemoteAddr());
        echoRequest.setUser_agent(request.getHeader("user-agent"));
        echoRequest.setMessage(message);


        RequestBody requestBody = RequestBody.create(
                okhttp3.MediaType.parse("application/json; charset=utf-8"), Utils.toJson(echoRequest));
        Request apiRequest = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        EchoResponse echoResponse;
        try {
            Response apiResponse = okHttpClient.newCall(apiRequest).execute();
            echoResponse = Utils.fromJson(apiResponse.body().string(), EchoResponse.class);

            return echoResponse;

        } catch (IOException e) {
            e.printStackTrace();
            echoResponse = new EchoResponse();
            echoResponse.setCode(500);
            return echoResponse;
        }

    }


}
