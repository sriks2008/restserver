package com.expedia.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RestEndpointHandler implements HttpHandler {
    private int invokeCount = 0;

    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "Invoked counter = " + invokeCount++  + "\n";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}