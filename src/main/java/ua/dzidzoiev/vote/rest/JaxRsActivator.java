package ua.dzidzoiev.vote.rest;/*
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

import com.wordnik.swagger.jaxrs.config.BeanConfig;
import ua.dzidzoiev.vote.rest.exception.AccessDeniedExceptionMapper;
import ua.dzidzoiev.vote.rest.exception.RuntimeExceptionMapper;
import ua.dzidzoiev.vote.security.rest.CORSFilter;
import ua.dzidzoiev.vote.security.rest.TokenFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * A class extending {@link javax.ws.rs.core.Application} and annotated with @ApplicationPath is the Java EE 7 "no XML" approach to activating
 * JAX-RS.
 *
 * <p>
 * Resources are served relative to the servlet path specified in the {@link javax.ws.rs.ApplicationPath} annotation.
 * </p>
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

    public JaxRsActivator() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("http://localhost:8080/InteractivePollWFB/rest");
//        beanConfig.setBasePath("http://voting-schaffedev.rhcloud.com/rest");
        beanConfig.setResourcePackage("ua.dzidzoiev.vote.rest");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        addRestResourceClasses(resources);
        addRestFilterClasses(resources);
        addRestExceptionMappers(resources);

        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);

        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(AuthResource.class);
        resources.add(CandidateResource.class);
        resources.add(RegionResource.class);
        resources.add(AccountResource.class);
        resources.add(TestResource.class);
    }

    private void addRestFilterClasses(Set<Class<?>> resources) {
        resources.add(TokenFilter.class);
        resources.add(CORSFilter.class);
    }

    private void addRestExceptionMappers(Set<Class<?>> resources) {
        resources.add(RuntimeExceptionMapper.class);
        resources.add(AccessDeniedExceptionMapper.class);
    }
}
