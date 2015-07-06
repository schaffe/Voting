package ua.dzidzoiev.vote.service.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by midnight coder on 06-Jul-15.
 */
@Qualifier
@Target({ TYPE, METHOD, FIELD, PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CurrentVoter {
}
