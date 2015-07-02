/**
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ua.dzidzoiev.vote.security;

import org.picketlink.Identity;
import org.picketlink.authorization.annotations.LoggedIn;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.credential.Token;
import org.picketlink.idm.credential.TokenCredential;
import org.picketlink.idm.model.Account;
import ua.dzidzoiev.vote.security.token.JWSToken;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.security.GeneralSecurityException;

@Stateless
public class AuthenticationService {

    public static final String USERNAME_PASSWORD_CREDENTIAL_CONTENT_TYPE = "application/x-authc-username-password+json";
    public static final String TOKEN_CONTENT_CREDENTIAL_TYPE = "application/x-authc-token";

    @Inject
    private Identity identity;

    @Inject
    private DefaultLoginCredentials credentials;

    @Inject
    private Token.Provider<JWSToken> tokenProvider;


    public String authenticateWithPassword(String login, String password) throws LoginException {
        if (!this.identity.isLoggedIn()) {
            this.credentials.setUserId(login);
            this.credentials.setPassword(password);
            this.identity.login();
        }

        Account account = this.identity.getAccount();

        if(account != null) {
            return issueToken(account).getToken();
        } else {
            throw new LoginException("Password authentication unsuccessful");
        }
    }

    public void authenticateWithToken(String token) throws LoginException {
        if(token == null || token.isEmpty()) {
            throw new LoginException("Token is empty");
        }

        if (!this.identity.isLoggedIn()) {
            this.credentials.setCredential(new TokenCredential(new JWSToken(token)));
            this.identity.login();
        }

        Account account = this.identity.getAccount();

        if (account == null) {
            throw new LoginException("Token authentication unsuccessful");
        }
    }

    @LoggedIn
    public void logout() throws GeneralSecurityException {
        Account account = this.identity.getAccount();

        this.tokenProvider.invalidate(account);

        this.identity.logout();
    }

    private Token issueToken(Account account) {
        return this.tokenProvider.issue(account);
    }
}
