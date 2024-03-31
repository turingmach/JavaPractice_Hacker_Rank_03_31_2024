package com.example.demo.food.outlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class URLConnectionTest {

    private static final String RESTURL = "https://jsonmock.hackerrank.com/api/food_outlets";

    public static String testGetConnection(String endpoint, String city, int page) throws IOException {
        String url = endpoint.concat("?city=").concat(city).concat("&page=").concat(String.valueOf(page));
        URL newUrl = URI.create(url).toURL();
        HttpURLConnection httpURLConnection = (HttpURLConnection) newUrl.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        int status = httpURLConnection.getResponseCode();
        if (status < 200 || status > 300)
            throw new IOException("Error in reading data with status" +status);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String response;
        StringBuilder sb = new StringBuilder();
        if ((response = bufferedReader.readLine()) != null) {
            sb.append(response);
        }
        bufferedReader.close();
        httpURLConnection.disconnect();
        return sb.toString();
    }

    public static void main(String args[]) {
        try {
            System.out.println("Result = "+ URLConnectionTest.testGetConnection(RESTURL, "Seattle", 1) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
