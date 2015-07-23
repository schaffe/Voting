/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package ua.dzidzoiev.vote.security;

import org.picketlink.Identity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.credential.Token;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;
import ua.dzidzoiev.vote.model.dto.AccountRegistration;
import ua.dzidzoiev.vote.model.dto.auth.AuthLoginElement;
import ua.dzidzoiev.vote.security.token.JWSToken;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

/**
 * <p>This class provides an abstraction point to the Identity Management operations required by the application./p>
 *
 * <p>The main objective of this class is avoid the spread use of the <code>IdentityManager</code> by different components of
 * the application and code duplication, providing a centralized point of access for the most common operations like create/update/query users and so forth.</p>
 *
 * <p>Also it is very useful to understand how PicketLink Identity Management is being used and what is being used by the application from a IDM perspective.</p>
 *
 * <p>Please note that PicketLink IDM provides a very flexible and poweful identity model and API, from which you can extend and fulfill your own requirements.</p>
 *
 * @author Pedro Igor
 */
@Stateless
public class IdentityModelManager {

    @Inject
    private Identity identity;

    @Inject
    private IdentityManager identityManager;

    @Inject
    private RelationshipManager relationshipManager;

    @Inject
    private Token.Provider<JWSToken> tokenProvider;

    public User findByLoginName(String loginName) {
        if (loginName == null) {
            throw new IllegalArgumentException("{security.login.invalid}");
        }

        return BasicModel.getUser(identityManager, loginName);
    }

    public User getLoggedInUser() {
        Account account = identity.getAccount();
        return (User) account;
    }

    /**
     * Creates new account, sets new random password if @param password is null or empty
     *
     * @param firstname
     * @param lastname
     * @param loginName
     * @param password
     * @return user login name and optional: password (if created random one)
     */
    public AuthLoginElement createAccount(@NotNull String firstname, @NotNull String lastname, @NotNull String loginName, String password) {
        User newUser = new User(loginName);
        newUser.setFirstName(firstname);
        newUser.setLastName(lastname);

        this.identityManager.add(newUser);

        AuthLoginElement result = new AuthLoginElement();
        result.setUsername(newUser.getLoginName());


        if (password == null || password.isEmpty()) {
            // TODO generateNewPassword
            password = "password";
        }
        updatePassword(newUser, password);

        return new AuthLoginElement(loginName, password);
    }

    public void removeUser(User user) {
        identityManager.remove(user);

//        User removedUserInstance = identityManager.getUser("admin");
//
//        assertNull(removedUserInstance);
    }

    public void removeUser(String username) {
        User user = findByLoginName(username);
        if (user != null) {
            identityManager.remove(user);
        } else {
            throw new IllegalArgumentException("{security.username.no_such_user}");
        }

//        User removedUserInstance = identityManager.getUser("admin");
//
//        assertNull(removedUserInstance);
    }

    public void updatePassword(Account account, String password) {
        this.identityManager.updateCredential(account, new Password(password));
    }

    public void grantRole(User account, String roleName) {
        Role storedRole = BasicModel.getRole(this.identityManager, roleName);
        BasicModel.grantRole(this.relationshipManager, account, storedRole);
    }

    public boolean hasRole(User account, String roleName) {
        Role storedRole = BasicModel.getRole(this.identityManager, roleName);
        return BasicModel.hasRole(this.relationshipManager, account, storedRole);
    }

    private Token issueToken(Account account) {
        return this.tokenProvider.issue(account);
    }

    private boolean isPasswordValid(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }
}
