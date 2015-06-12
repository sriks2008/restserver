package com.expedia.web;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String [] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new RestEndpointHandler());
        server.createContext("/delay", new DelayedResponseHandler(10));
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
    }
}
