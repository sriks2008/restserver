package com.expedia.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client {
    public static void main(String [] args) throws Exception {
        try {
            URL url = new URL("http://localhost:8000/test");
            HttpURLConnection urlConnection = (HttpURLConnection)
                    url.openConnection();

            urlConnection.addRequestProperty("User-Agent", "ClientX");
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Accept", "text/xml");
            urlConnection.setRequestProperty("Accept", "text/plain");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            InputStreamReader inStream = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader bf =  new BufferedReader(inStream);

            while (bf.ready()) {
                String line = bf.readLine();
                System.out.println(line);
            }
            System.out.println("Response code: " + urlConnection.getResponseCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
