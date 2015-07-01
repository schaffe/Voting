package ua.dzidzoiev.vote.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

@Provider
@PreMatching
public class SecurityRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger(SecurityRequestFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
//
//        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
//
//        Method method = methodInvoker.getMethod();
//        Class c = methodInvoker.getResourceClass();
//
//        if( ! method.isAnnotationPresent(PermitAll.class)) {
//            //Access denied for all
//            if(method.isAnnotationPresent(DenyAll.class))
//            {
//                requestContext.abortWith(Response.status(HttpResponseCodes.SC_FORBIDDEN).build());
//                return;
//            }
//
//
//            String path = requestContext.getUriInfo().getPath();
//            log.info("Filtering request path: " + path);
//
//            // IMPORTANT!!! First, Acknowledge any pre-flight test from browsers for this case before validating the headers (CORS stuff)
//            if (requestContext.getRequest().getMethod().equals("OPTIONS")) {
//                requestContext.abortWith(Response.status(Response.Status.OK).build());
//
//                return;
//            }
//
//            // Then check is the service key exists and is valid.
//            DemoAuthenticator demoAuthenticator = DemoAuthenticator.getInstance();
//            String serviceKey = requestContext.getHeaderString(AuthResource.SERVICE_KEY);
//
//            if (!demoAuthenticator.isServiceKeyValid(serviceKey)) {
//                // Kick anyone without a valid service key
//                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//
//                return;
//            }
//
//            //Verify user access
//            if(method.isAnnotationPresent(RolesAllowed.class))
//            {
//                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
//                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
//
//                //Is user valid?
////                if( ! isUserAllowed(username, password, rolesSet))
////                {
////                    requestContext.abortWith(Response.status(HttpResponseCodes.SC_UNAUTHORIZED).build());
////                    return;
////                }
//            }
//        }
    }

    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet)
    {
        boolean isAllowed = false;

        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);
        String userRole = "ADMIN";

        //Step 2. Verify user role
        if(rolesSet.contains(userRole))
        {
            isAllowed = true;
        }
        return isAllowed;
    }
}
