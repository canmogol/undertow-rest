package com.fererlab.undertow;

import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Server {

    private Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().readConfiguration(Server.class.getClassLoader().getResourceAsStream("logging.properties"));
        Server server = new Server();
        server.run();
    }

    private void run() throws IOException {

        long time = System.currentTimeMillis();
        UndertowJaxrsServer server = new UndertowJaxrsServer();

        logger.info("server created: " + (System.currentTimeMillis() - time) + " milli seconds");
        time = System.currentTimeMillis();

        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.setInjectorFactoryClass("org.jboss.resteasy.cdi.CdiInjectorFactory");
        deployment.setApplicationClass(SingleApplication.class.getName());

        DeploymentInfo deploymentInfo = server.undertowDeployment(deployment, "/api");
        deploymentInfo.setDisplayName("cdi");
        deploymentInfo.setClassLoader(Server.class.getClassLoader());
        deploymentInfo.setContextPath("/cdi");
        deploymentInfo.setDeploymentName("cdi example");
        deploymentInfo.addListeners(Servlets.listener(org.jboss.weld.environment.servlet.Listener.class));

        server.deploy(deploymentInfo);
        logger.info("server deployed: " + (System.currentTimeMillis() - time) + " milli seconds");

        server.start();

//        time = System.currentTimeMillis();
//        Client client = ClientBuilder.newClient();
//        logger.info("client created: " + (System.currentTimeMillis() - time) + " milli seconds");
//
//        time = System.currentTimeMillis();
//        String response = client.target(TestPortProvider.generateURL("/cdi/api/inject/hi/JOHN")).request().get(String.class);
//        logger.info("request completed: " + (System.currentTimeMillis() - time));
//        logger.info("RESPONSE: " + response);
//
//        time = System.currentTimeMillis();
//        client.close();
//        logger.info("client closed: " + (System.currentTimeMillis() - time) + " milli seconds");
//
//        time = System.currentTimeMillis();
//        server.stop();
//        logger.info("server stopped: " + (System.currentTimeMillis() - time) + " milli seconds");

    }


}
