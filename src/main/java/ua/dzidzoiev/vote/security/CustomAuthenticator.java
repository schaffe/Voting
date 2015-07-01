/**
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 * <p/>
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

import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.authentication.LockedAccountException;
import org.picketlink.authentication.UnexpectedCredentialException;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.credential.*;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * <p>A simple authenticator that supports two credential types: username/password or a simple token.</p>
 */
@RequestScoped
@PicketLink
public class CustomAuthenticator extends BaseAuthenticator {


    @Inject
    private ServiceCredentials credentials;

    @Inject
    private SecurityManager securityManager;

    @Inject
    Instance<IdentityManager> identityManager;

//    @Override
//    public void authenticate() {
//        if (this.credentials.getServiceKey() == null) {
//            return;
//        }
//
//        String serviceKey = this.credentials.getServiceKey();
//        try {
//            if (securityManager.isServiceKeyValid(serviceKey)) {
//                if (isLoginPasswordCredential()) {
//                    String login = this.credentials.getLogin();
//                    String password = this.credentials.getPassword();
//
//                    String token = securityManager.login(serviceKey, login, password);
//                    this.credentials.setToken(token);
//
//                    successfulAuthentication();
//                } else {
//                    String token = credentials.getToken();
//                    if(securityManager.isAuthTokenValid(serviceKey, token))
//                        successfulAuthentication();
//                }
//
//            }
//        } catch (LoginException e) {
//            setStatus(AuthenticationStatus.FAILURE);
//        }
//    }

    private boolean isLoginPasswordCredential() {
        return this.credentials.getLogin() != null;
    }

//    private boolean isTokenCredential() {
//        return ServiceTokenCredential.class.equals(credentials.getCredential().getClass());
//    }

//    private User getDefaultUser() {
//        return new User("jane");
//    }

    private void successfulAuthentication() {
        setStatus(AuthenticationStatus.SUCCESS);
//        setAccount(getDefaultUser());
    }

    public void authenticate() {
        if (credentials.getCredential() == null) {
            return;
        }

        Credentials creds;

        if (isUsernamePasswordCredential()) {
            creds = new UsernamePasswordCredentials(credentials.getLogin(),
                    (Password) credentials.getCredential());
        } else if (isTokenCredential()) {
            creds = new TokenCredential((Token) credentials.getCredential());
        }

//        else if (isDigestCredential()) {
//            creds = new DigestCredentials((Digest) credentials.getCredential());
//        } else if (isCustomCredential()) {
//            creds = (Credentials) credentials.getCredential();
//        }

        else {
            throw new UnexpectedCredentialException("Unsupported credential type [" + credentials.getCredential() + "].");
        }

        identityManager.get().validateCredentials(creds);

        this.credentials.setStatus(creds.getStatus());
        this.credentials.setValidatedAccount(creds.getValidatedAccount());

        if (Credentials.Status.VALID.equals(creds.getStatus())) {
            setStatus(AuthenticationStatus.SUCCESS);
            setAccount(creds.getValidatedAccount());
        } else if (Credentials.Status.ACCOUNT_DISABLED.equals(creds.getStatus())) {
            throw new LockedAccountException("Account [" + this.credentials.getLogin() + "] is disabled.");
        }
    }

    private boolean isCustomCredential() {
        return Credentials.class.isInstance(credentials.getCredential());
    }

    private boolean isDigestCredential() {
        return Digest.class.equals(credentials.getCredential().getClass());
    }

    private boolean isUsernamePasswordCredential() {
        return Password.class.equals(credentials.getCredential().getClass()) && credentials.getLogin() != null;
    }

    private boolean isTokenCredential() {
        return Token.class.equals(credentials.getCredential().getClass());
    }

}
