package com.expedia.web;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class RestEndpointHandler implements HttpHandler {
    private int invokeCount = 0;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String input=exchange.getRequestURI().toASCIIString();//get the URL
        System.out.println("Request Method: " + exchange.getRequestMethod());
        Headers headers = exchange.getRequestHeaders();
        for (String headerName : headers.keySet()) {
            System.out.println("Header : " + headerName + " " + headers.get(headerName));
        }

        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><counter>"+(invokeCount++)+"</counter>";
        exchange.getResponseHeaders().add("Content-Type", "application/xml");
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        exchange.close();
    }
}