package echo;


import common.EchoRequest;
import common.EchoResponse;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by nnkwrik
 * 18/08/10 9:18
 */
@ApplicationScoped
public class EchoService {

    public EchoResponse getEchoResp(EchoRequest request) {

        //log
        System.out.println(request.getIp() + " : " + request.getMessage());

        EchoResponse response = new EchoResponse();
        response.setCode(200);
        response.setMessage(request.getMessage());

        return response;
    }
}
