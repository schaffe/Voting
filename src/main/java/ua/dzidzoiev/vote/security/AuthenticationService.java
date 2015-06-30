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
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.model.Account;
import ua.dzidzoiev.vote.rest.DemoAuthenticator;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public class AuthenticationService {

    public static final String USERNAME_PASSWORD_CREDENTIAL_CONTENT_TYPE = "application/x-authc-username-password+json";
    public static final String TOKEN_CONTENT_CREDENTIAL_TYPE = "application/x-authc-token";

    @Inject
    private Identity identity;

    @Inject
    private DefaultLoginCredentials credentials;

    @Inject
    DemoAuthenticator demoAuthenticator;

    public Response authenticate(DefaultLoginCredentials credential) {
        if (!this.identity.isLoggedIn()) {
            this.credentials.setUserId(credential.getUserId());
            this.credentials.setPassword(credential.getPassword());
            this.identity.login();
        }

        Account account = this.identity.getAccount();

        return Response.ok().entity(account).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public Response authenticate(String token) {
        if (!this.identity.isLoggedIn()) {
            ServiceTokenCredential credential = new ServiceTokenCredential(token);

            this.credentials.setCredential(credential);

            this.identity.login();
        }

        Account account = this.identity.getAccount();

        return Response.ok().entity(account).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
