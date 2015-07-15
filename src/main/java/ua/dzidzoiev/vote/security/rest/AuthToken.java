package ua.dzidzoiev.vote.security.rest;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Allows security checking by authentication token.
 * This annotation only indicates that methods in th class need to be authenticated by Token.
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@NameBinding
public @interface AuthToken {
}
