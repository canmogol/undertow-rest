package com.fererlab.undertow;

import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.test.TestPortProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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

    private void run() {

        long time = System.currentTimeMillis();
        UndertowJaxrsServer server = new UndertowJaxrsServer().start();
        logger.info("server created: " + (System.currentTimeMillis() - time) + " milli seconds");

        time = System.currentTimeMillis();
        server.deploy(SingleApplication.class, "/api");
        logger.info("server deployed: " + (System.currentTimeMillis() - time) + " milli seconds");

        time = System.currentTimeMillis();
        Client client = ClientBuilder.newClient();
        logger.info("client created: " + (System.currentTimeMillis() - time) + " milli seconds");

        time = System.currentTimeMillis();
        String response = client.target(TestPortProvider.generateURL("/api/hi")).request().get(String.class);
        logger.info("request completed: " + (System.currentTimeMillis() - time) + " response: " + response);

        time = System.currentTimeMillis();
        client.close();
        logger.info("client closed: " + (System.currentTimeMillis() - time) + " milli seconds");

        time = System.currentTimeMillis();
        server.stop();
        logger.info("server stopped: " + (System.currentTimeMillis() - time) + " milli seconds");

    }

}
