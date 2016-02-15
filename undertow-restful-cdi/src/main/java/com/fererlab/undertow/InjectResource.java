package com.fererlab.undertow;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/inject")
public class InjectResource {

    @Inject
    private InjectBean injectBean;

    @GET
    @Path("hi/{name}")
    @Produces("text/plain")
    public String sayHi(@PathParam("name") String name) {
        return injectBean.sayHi(name);
    }

}
