package com.rohitchouhan35.springemailaaptatt;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CityTemperature {

    public static String getData() throws IOException, InterruptedException {
        String result = "";

        int tempAhmedabad = getTemperature("Ahmedabad");

        result += "Ahmedabad " + String.valueOf(tempAhmedabad);

        return result;
    }
    public static int getTemperature(String city) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weather-by-api-ninjas.p.rapidapi.com/v1/weather?city="+city))
                .header("X-RapidAPI-Key", "")
                .header("X-RapidAPI-Host", "weather-by-api-ninjas.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();
//		System.out.println(responseBody);

        int temp = extractTemperature(responseBody);
        return temp;
    }

    public static int extractTemperature(String jsonString) {
        int tempIndex = jsonString.indexOf("\"temp\":");
        if (tempIndex != -1) {
            int startIndex = tempIndex + "\"temp\":".length();
            int endIndex = jsonString.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = jsonString.indexOf("}", startIndex);
            }
            String tempValue = jsonString.substring(startIndex, endIndex).trim();
            return Integer.parseInt(tempValue);
        }
        return 0;
    }
}
