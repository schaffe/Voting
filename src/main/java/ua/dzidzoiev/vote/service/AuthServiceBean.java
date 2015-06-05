package ua.dzidzoiev.vote.service;

import ua.dzidzoiev.vote.model.User;
import ua.dzidzoiev.vote.model.dto.auth.AuthAccessElement;
import ua.dzidzoiev.vote.model.dto.auth.AuthLoginElement;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.UUID;

@Stateless(name = "AuthService")
public class AuthServiceBean implements AuthService {

    @Inject
    UserService userService;

    @Override
    public AuthAccessElement login(AuthLoginElement loginElement) {
        User user = userService.findByUsernameAndPassword(loginElement.getUsername(), loginElement.getPassword());
        if (user != null) {
            String authToken = UUID.randomUUID().toString();
            user.setAuthToken(authToken);
            userService.save(user);
            return new AuthAccessElement(loginElement.getUsername(), authToken, user.getRole());
        }
        return null;
    }
}