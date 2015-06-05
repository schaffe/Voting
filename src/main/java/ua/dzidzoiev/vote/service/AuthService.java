package ua.dzidzoiev.vote.service;

import ua.dzidzoiev.vote.model.dto.auth.AuthAccessElement;
import ua.dzidzoiev.vote.model.dto.auth.AuthLoginElement;

/**
 * Created by midnight coder on 03-Jun-15.
 */
public interface AuthService {
    AuthAccessElement login(AuthLoginElement loginElement);
}
