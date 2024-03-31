package com.example.demo.food.outlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FoodOutletServiceTest {

    private static final String RESTURL = "https://jsonmock.hackerrank.com/api/food_outlets";

    public static void main(String args[]) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//            String city = bufferedReader.readLine();;
//            int vote = Integer.parseInt(bufferedReader.readLine().trim());
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("Seattle", "Cafe Juanita");
        resultMap.put("Miami", "Pirates of Grill");
        resultMap.put("Chicago", "AB's - Absolute Barbecues");
        resultMap.put("Austin", "Yanki Sizzlers");
        resultMap.put("Dallas", "AB's - Absolute Barbecues");
        resultMap.put("Portland", "Cafe Campagne");
        resultMap.put("Boston", "Uncle Jack's");
        resultMap.put("Omaha", "Barbeque Nation");
        resultMap.put("Detroit", "");
        FoodOutletService foodOutletService = new FoodOutletService();
        for (Map.Entry entry: resultMap.entrySet()) {
            int vote = 500;
            String city = (String) entry.getKey();
            System.out.println(city);
            String result = foodOutletService.finestFoodOutlet(city, vote);
            if (result == null && resultMap.get(city) == null) {
                System.out.println(resultMap.get(city));
                System.out.println("Pass");
            }else if (result.equalsIgnoreCase(resultMap.get(city))) {
                System.out.println(result);
                System.out.println("Pass");
            } else {
                System.out.println(result);
                System.out.println("Failed!");
            }
        }
//        for (int i = 0; i <result.size(); i++) {
//            bufferedWriter.write(result.get(i));
//            if (i != result.size() -1) {
//                bufferedWriter.write("\n");
//            }
//        }
//        bufferedWriter.newLine();
//        bufferedReader.close();
//        bufferedWriter.close();
    }




    //    public static List<String> getRelevantFoodOutlets(String city, int maxCost) {
//        int page = 1;
//        List<String> cityList = new ArrayList<>();
//        try {
//            cityList =  getRelevantFoodOutlets(city, page, maxCost);
//        } catch (Exception e) {
//            System.out.println("Exception" +e);
//        }
//        return cityList;
//
//    }

//    public static List<String> getCities(String city, int page, int maxCost) throws Exception {
//        List<String> cityList = new ArrayList<>();
//        String cityName = null;
//        String response = getResponsePerPage(RESTURL, city, page);
//        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
//        int totalPages = jsonObject.get("total_pages").getAsInt();
//        JsonArray data = jsonObject.getAsJsonArray("data");
//        for (JsonElement e : data) {
//            if (e.getAsJsonObject().get("estimated_cost").getAsInt() < maxCost) {
//                cityName = e.getAsJsonObject().get("name").getAsString();
//                System.out.println("cityName " + cityName);
//                cityList.add(cityName);
//            }
//        }
//        return totalPages==page? cityList : getCities(city, page+1, maxCost);
//    }


}

