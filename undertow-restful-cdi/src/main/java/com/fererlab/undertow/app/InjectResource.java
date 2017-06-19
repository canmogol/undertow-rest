package com.fererlab.undertow.app;

import com.fererlab.undertow.property.Property;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/inject")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/inject", description = "injection example api")
public class InjectResource {

    @Inject
    private InjectBean injectBean;

    @Inject
    @Property
    private String key1;

    @Inject
    @Property("application.name")
    private String applicationName;

    @GET
    @Path("hi/{name}")
    @ApiOperation(value = "hi/{name}", notes = "says Hi! to the given name")
    public Map<String, String> sayHi(@PathParam("name") String name) {
        Map<String, String> response = new HashMap<>();
        response.put("name", injectBean.sayHi(name));
        response.put("key1", key1);
        response.put("application", applicationName);
        return response;
    }

}
