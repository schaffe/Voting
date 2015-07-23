package ua.dzidzoiev.vote.service.annotation.validation;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by midnight coder on 06-Jul-15.
 */
@Qualifier
@Target({ FIELD, PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Registered {
}
