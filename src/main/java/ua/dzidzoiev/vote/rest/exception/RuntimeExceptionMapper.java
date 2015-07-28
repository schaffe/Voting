package ua.dzidzoiev.vote.rest.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import ua.dzidzoiev.vote.util.MessageBuilder;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by midnight coder on 24-Jul-15.
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<java.lang.RuntimeException> {
    @Override
    public Response toResponse(java.lang.RuntimeException arg0) {
        Throwable cause = ExceptionUtils.getRootCause(arg0);
        MessageBuilder messageBuilder;
        if(cause instanceof org.apache.deltaspike.security.api.authorization.AccessDeniedException) {
            messageBuilder = MessageBuilder.accessDenied();
        } else {
            messageBuilder = MessageBuilder.badRequest().message(cause.getMessage());
        }
        return messageBuilder.build();
    }
}
