package com.expedia.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class DelayedResponseHandler implements HttpHandler {
    private int invokeCount = 0;
    private int secondsToSleep;
    public DelayedResponseHandler(int secondsToSleep) {
        this.secondsToSleep = secondsToSleep;
    }

    @Override
    public void handle(HttpExchange t) throws IOException{
        try {
            Thread.sleep(secondsToSleep * 1000);
        } catch (Exception ex) {
            System.out.println("Hey common this is just for use on your laptop. :-)");
            ex.printStackTrace();
        }
        String response = "Invoked counter = " + invokeCount++  + "\n";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}