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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.jboss.resteasy.spi.validation.ValidateRequest;
import ua.dzidzoiev.vote.data.RegionRepository;
import ua.dzidzoiev.vote.model.Region;
import ua.dzidzoiev.vote.model.dto.Statistics;
import ua.dzidzoiev.vote.security.rest.AuthToken;
import ua.dzidzoiev.vote.service.RegionService;
import ua.dzidzoiev.vote.service.StatisticsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("/regions")
@Consumes("*/*")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@Api(value = "/regions", description = "Region resource")
public class RegionResource {
    @Inject
    private Logger log;

    @Inject
    private StatisticsService statisticsService;

    @Inject
    private RegionService regionService;

    @GET
    @ApiOperation(value = "", notes = "Get all regions")
    public List<Region> listAll() {
        List<Region> regions = regionService.getAll();
        return regions;
    }

    @GET
    @Path("/{region-code}/stats")
    @AuthToken
    @ApiOperation(value = "/{region-code}/stats", notes = "Get statistics related to some region")
    public List<Statistics> getStats(@PathParam("region-code") String code) {
        return statisticsService.getHotStats(code);
    }

    @POST
    @AuthToken
    @ApiOperation(value = "", notes = "Create new region")
    @ValidateRequest
    public Region create(@Valid Region city) {
        regionService.create(city);
        return city;
    }


//    @PUT
//    @Path("/{id:[0-9][0-9]*}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateCity(City city) {
//        Response.ResponseBuilder builder = null;
//        dao.update(city);
//        builder = Response.ok();
//        return builder.build();
//    }
//
//    @DELETE
//    @Path("/{id:[0-9][0-9]*}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public Response deleteCity(@PathParam("id") int id) {
//        Response.ResponseBuilder builder = null;
//        City city = dao.get(id);
//        if (city == null) {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//        dao.delete(city);
//        builder = Response.ok();
//        return builder.build();
//    }
}
