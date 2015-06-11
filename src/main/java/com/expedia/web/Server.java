package com.expedia.web;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private HttpServer server;

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new RestEndpointHandler());
        server.createContext("/delay", new DelayedResponseHandler(10));
        server.setExecutor(null);
        server.start();
    }

    public void stop() {
        server.stop(0);
    }

    public static void main(String [] args) throws Exception {
        Server server = new Server();
        server.start();
    }
}
