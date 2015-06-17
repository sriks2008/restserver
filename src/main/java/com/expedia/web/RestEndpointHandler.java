package com.expedia.web;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class RestEndpointHandler implements HttpHandler {
    public static Map<Integer, String> map = new HashMap<Integer, String>();
    private int invokeCount = 0;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String input=exchange.getRequestURI().toASCIIString();
        Map<String, Object> params = (Map<String, Object>)exchange.getAttribute("parameters");
        System.out.println("Request Method: " + exchange.getRequestMethod() + " URI: " + input);
        Headers headers = exchange.getRequestHeaders();
        for (String headerName : headers.keySet()) {
            System.out.println("Header : " + headerName + " " + headers.get(headerName));
        }

        String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><counter>"+(invokeCount)+"</counter>";
        exchange.getResponseHeaders().add("Content-Type", "application/xml");
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());

        map.put(invokeCount++, params.toString());

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        exchange.close();
    }

    public String getDataFromBody(HttpExchange exchange) throws IOException {
        InputStream in = exchange.getRequestBody();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte buf[] = new byte[4096];
            for (int n = in.read(buf); n > 0; n = in.read(buf)) {
                out.write(buf, 0, n);
            }
            return new String(out.toByteArray(), "UTF-8");
        } finally {
            in.close();
        }
    }
}