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

public class FoodOutletService {

    private static final String RESTURL = "https://jsonmock.hackerrank.com/api/food_outlets";

    public String finestFoodOutlet(String city, int minVotes) {
        int page = 1;
        String restaurant = "";
        int votes = 0;
        double avgRating = 0.0;
        try {
            restaurant = getRestaurant(restaurant, city, 0, 0.0, page, minVotes);
        } catch (Exception e) {

        }
        return restaurant;
    }

    String getRestaurant(String restaurant, String city, int votes, double avgRating, int page, int minVotes) throws IOException {
        String response = getResponsePerPage(RESTURL, city, page);
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        int totalPages = jsonObject.get("total_pages").getAsInt();
        if (totalPages == 0) {
            return "";
        }
        JsonArray data = jsonObject.getAsJsonArray("data");
        for (JsonElement e : data) {
            String name = e.getAsJsonObject().get("name").getAsString();
            JsonElement userRating = e.getAsJsonObject().get("user_rating");
            int restVotes = userRating.getAsJsonObject().get("votes").getAsInt();
            double restRating = userRating.getAsJsonObject().get("average_rating").getAsDouble();
            if (restVotes >= minVotes) {
                if (restRating >  avgRating) {
                    restaurant = name;
                    avgRating = restRating;
                    votes = restVotes;
                } else if (restRating == avgRating && restVotes > votes) {
                    restaurant = name;
                    avgRating = restRating;
                    votes = restVotes;
                }
            }
        }
        String result = totalPages == page ? restaurant : getRestaurant(restaurant, city, votes, avgRating, page + 1, minVotes);
//        System.out.println("city = "+ city + " restaurant = "+ restaurant + " votes = " + votes + " rating = " +avgRating );
        return result;
    }

    private String getResponsePerPage(String resturl, String city, int page) throws IOException {
        String endpoint = resturl.concat("?city=").concat(city).concat("&page=").concat(String.valueOf(page));
        URL url = URI.create(endpoint).toURL();
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        int status = httpURLConnection.getResponseCode();
        if (status <200 || status >300) {
            throw new IOException("Error while reading data");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String response;
        StringBuilder stringBuilder =new StringBuilder();
        while ((response = bufferedReader.readLine()) != null) {
            stringBuilder.append(response);
        }
        bufferedReader.close();
        httpURLConnection.disconnect();
        return stringBuilder.toString();
    }

}
