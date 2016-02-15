package com.fererlab.undertow;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hi")
public class HelloResource {

    @GET
    @Produces("text/plain")
    public String sayHi() {
        return "HI THERE";
    }

}
