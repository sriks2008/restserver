package com.expedia.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class RestEndpointSummaryHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        int size = RestEndpointHandler.map.size();
        StringBuffer response = new StringBuffer();
        for (String str : RestEndpointHandler.map.values()) {
            response.append(str);
        }
        String responseStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><counter>"+(size)+"</counter>"+response.toString();

        exchange.getResponseHeaders().add("Content-Type", "application/xml");
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());

        OutputStream os = exchange.getResponseBody();
        os.write(responseStr.getBytes());
        exchange.close();

    }
}
