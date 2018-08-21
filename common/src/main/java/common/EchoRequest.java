package common;

/**
 * Created by nnkwrik
 * 18/08/10 9:22
 */
public class EchoRequest {

//    @NotNull
    public String ip;

//    @NotNull
    public String user_agent;
//
//    @NotNull
//    @Size(min = 1)
    public String message;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser_agent() {
        return user_agent;
    }

    public void setUser_agent(String user_agent) {
        this.user_agent = user_agent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
