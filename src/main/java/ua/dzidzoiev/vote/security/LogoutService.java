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
import ua.dzidzoiev.vote.rest.filter.Token;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * <p>Simple logout service.</p>
 */
@Path("/logout")
@Token
@LoggedIn
public class LogoutService {

    @Inject
    private Identity identity;

    @POST
    public void logout() {
        if (this.identity.isLoggedIn()) {
            this.identity.logout();
        }
    }

}
