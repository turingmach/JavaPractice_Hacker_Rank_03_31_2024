package com.example.demo.food.outlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class FinestFoodOutletService {

    private static final String RESTURL = "https://jsonmock.hackerrank.com/api/food_outlets";

    public static String getFinestFoodOutlets(String city, int minVotes) {
        int page = 1;
        String restaurant = "";
        try {
            restaurant = getRestaurant(restaurant, city, 0, 0.0, page, minVotes);
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
        return restaurant;
    }

    static String getRestaurant(String restaurant, String city, int votes, double avgRating, int page, int minVotes)
            throws IOException {
        String response = getResponsePerPage(RESTURL, city, page);
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        int totalPages = jsonObject.get("total_pages").getAsInt();
        if (totalPages == 0) {
            return "";
        }
        JsonArray data = jsonObject.getAsJsonArray("data");
        for (JsonElement e : data) {
            String name = e.getAsJsonObject().get("name").getAsString();
            JsonElement user_rating = e.getAsJsonObject().get("user_rating");
            int restVotes = user_rating.getAsJsonObject().get("votes").getAsInt();
            double averageRating = user_rating.getAsJsonObject().get("average_rating").getAsDouble();
            if (restVotes > minVotes) {
                if (averageRating > avgRating) {
                    restaurant = name;
                    avgRating = averageRating;
                    votes = restVotes;
                } else if (averageRating == avgRating) {
                    if (restVotes > votes) {
                        restaurant = name;
                        avgRating = averageRating;
                        votes = restVotes;
                    }
                }
            }
        }
        String result = totalPages == page ? restaurant : getRestaurant(restaurant, city, votes, avgRating, page + 1, minVotes);
//        System.out.println("city = "+ city + " restaurant = "+ restaurant + " votes = " + votes + " rating = " +avgRating );
        return result;
    }

    static String getResponsePerPage(String endpoint, String city, int page) throws IOException {
        validatePageInput(page);
        String newUrl = endpoint.concat("?city=" + city + "&page=" + page);
        URL url = URI.create(newUrl).toURL();
        HttpURLConnection conn = getHttpURLConnection(url);
        validateResponseStatus(conn);
        String response = getResponse(conn);
        conn.disconnect();
        return response;
    }

    static HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.addRequestProperty("Content-Type", "application/json");
        return conn;
    }

    static String getResponse(HttpURLConnection conn) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response;
        while ((response = br.readLine()) != null) {
            sb.append(response);
        }
        br.close();
        return sb.toString();
    }

    static void validateResponseStatus(HttpURLConnection conn) throws IOException {
        int status = conn.getResponseCode();
        if (status < 200 || status > 300) {
            throw new IOException("Error in reading data with status" + status);
        }
    }

    static void validatePageInput(int page) throws IOException {
        if (page < 1) {
            throw new IOException("Invalid Page Number" + page);
        }
    }
}