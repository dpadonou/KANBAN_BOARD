import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import web.*;
import web.doc.SwaggerResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class ClassProvider extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> classSet = new HashSet<>();

        classSet.add(BoardRessource.class);
        classSet.add(CardRessource.class);
        classSet.add(GitCardRessource.class);
        classSet.add(SectionRessource.class);
        classSet.add(UserRessource.class);

        classSet.add(OpenApiResource.class);
        classSet.add(SwaggerResource.class);

        return classSet;
    }

}
