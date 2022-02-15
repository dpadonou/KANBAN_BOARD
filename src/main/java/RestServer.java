import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import java.util.logging.Logger;

/**
 * RESTfull microservice, based on JAX-RS and JBoss Undertow
 */
public class RestServer {

    private static final Logger logger = Logger.getLogger(RestServer.class.getName());

    public static void main(String[] args) {

        UndertowJaxrsServer jaxrsServer = new UndertowJaxrsServer();

        ClassProvider provider = new ClassProvider();

        jaxrsServer.deploy(provider);

        jaxrsServer.start(
            Undertow
                .builder()
                .addHttpListener(8080, "localhost")
        );

        logger.info("JAX-RS based micro-service running!");
    }
}