package ua.dzidzoiev.vote.rest.filter;

import javax.ws.rs.NameBinding;
import java.lang.annotation.*;

/**
 * Allows security checking by authentication token.
 * This annotation only indicates that methods in th class need to be authenticated by Token.
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
@NameBinding
public @interface Token {
}
