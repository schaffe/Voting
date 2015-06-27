package ua.dzidzoiev.vote.rest.filter;

import javax.ws.rs.NameBinding;
import java.lang.annotation.*;

/**
 * Created by midnight coder on 05-Jun-15.
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
@NameBinding
public @interface Token {
}
