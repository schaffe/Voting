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
package ua.dzidzoiev.vote.security.token;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Token;
import org.picketlink.idm.credential.storage.TokenCredentialStorage;
import org.picketlink.idm.model.Account;
import org.picketlink.idm.model.basic.Realm;

import javax.ejb.Stateless;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import java.util.UUID;

@Stateless
@Vetoed
public class ServiceTokenProvider implements Token.Provider<ServiceToken> {

    @Inject
    private PartitionManager partitionManager;

    @Override
    public ServiceToken issue(Account account) {
        TokenCredentialStorage tokenCredentialStorage = getIdentityManager().retrieveCurrentCredential(account, TokenCredentialStorage.class);
        ServiceToken token;

        if (tokenCredentialStorage == null) {
            String tokenString = UUID.randomUUID().toString();
            token = new ServiceToken(tokenString, account.getId());
            // now we update the account with the token previously issued by this provider.
            getIdentityManager().updateCredential(account, token);
        } else {
            token = new ServiceToken(tokenCredentialStorage.getToken(), account.getId());
        }

        return token;
    }

    @Override
    public ServiceToken renew(Account account, ServiceToken renewToken) {
        return issue(account);
    }

    @Override
    public void invalidate(Account account) {
        getIdentityManager().removeCredential(account, TokenCredentialStorage.class);
    }

    @Override
    public Class<ServiceToken> getTokenType() {
        return ServiceToken.class;
    }

    private byte[] getPrivateKey() {
        return getPartition().<byte[]>getAttribute("PrivateKey").getValue();
    }

    private int getCurrentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    private Realm getPartition() {
        return partitionManager.getPartition(Realm.class, Realm.DEFAULT_REALM);
    }

    private IdentityManager getIdentityManager() {
        return this.partitionManager.createIdentityManager(getPartition());
    }
}
