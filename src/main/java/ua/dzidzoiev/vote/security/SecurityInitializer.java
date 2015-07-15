/*
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

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import static org.picketlink.idm.model.basic.BasicModel.grantRole;
import static ua.dzidzoiev.vote.security.ApplicationRoles.*;

/**
 * This startup bean creates a number of default users, groups and roles when the application is started.
 * 
 * @author Shane Bryzak
 */
@Singleton(name = "SecurityInitializer")
@Startup
public class SecurityInitializer {

    @Inject
    private PartitionManager partitionManager;

    @PostConstruct
    public void create() {

        User admin = new User("admin");
        admin.setEmail("admin@acme.com");
        admin.setFirstName("John");
        admin.setLastName("Smith");

        IdentityManager identityManager = this.partitionManager.createIdentityManager();

        identityManager.add(admin);
        identityManager.updateCredential(admin, new Password("admin"));

        User statistics = new User("stats");
        statistics.setEmail("statistics@acme.com");
        statistics.setFirstName("Mary");
        statistics.setLastName("Jones");
        identityManager.add(statistics);
        identityManager.updateCredential(statistics, new Password("stats"));

        User voter = new User("voter");
        voter.setEmail("voter@acme.com");
        voter.setFirstName("Jane");
        voter.setLastName("Doe");
        identityManager.add(voter);
        identityManager.updateCredential(voter, new Password("voter"));

        Role admin_role = new Role(ADMIN);
        identityManager.add(admin_role);

        Role stats_role = new Role(STATISTIC_VIEWER);
        identityManager.add(stats_role);

        Role voter_role = new Role(VOTER);
        identityManager.add(voter_role);

//        // Create group "sales"
//        Group sales = new Group("sales");
//        identityManager.add(sales);

        RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();

//        // Make john a member of the "sales" group
//        addToGroup(relationshipManager, admin, sales);
//
//        // Make mary a manager of the "sales" group
//        grantGroupRole(relationshipManager, statistics, admin_role, sales);

        // Grant the "superuser" application role to jane
        grantRole(relationshipManager, voter, voter_role);
        grantRole(relationshipManager, admin, admin_role);
        grantRole(relationshipManager, statistics, stats_role);
    }
}
