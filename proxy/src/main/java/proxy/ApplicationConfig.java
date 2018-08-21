package proxy;





import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;


/**
 * Created by nnkwrik
 * 18/08/12 17:41
 */
@ApplicationPath("/")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(ProxyResource.class);
        return resources;
    }
}
