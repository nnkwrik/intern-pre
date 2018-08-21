package echo;





import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;


/**
 * Created by nnkwrik
 * 18/08/11 12:45
 */
@ApplicationPath("/")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(EchoResource.class);
        return resources;
    }
}
