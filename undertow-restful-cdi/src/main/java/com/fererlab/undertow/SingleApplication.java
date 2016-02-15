package com.fererlab.undertow;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class SingleApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(InjectResource.class);
        return classes;
    }

}
