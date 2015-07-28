package ua.dzidzoiev.vote.security.annotations;

import org.apache.deltaspike.security.api.authorization.SecurityParameterBinding;

import java.lang.annotation.*;


@Retention(value = RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Documented
@SecurityParameterBinding
public @interface CurrentUserParameterBinding {
}

