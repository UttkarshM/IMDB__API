package com.example.demo.WebRequests;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestApi{
    public static void getImdbData() throws Exception {
        getImdbData1();
    }
    public static void getImdbData1() throws Exception {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://imdb8.p.rapidapi.com/title/v2/get-popular-movies-by-genre?genre=adventure&limit=100"))
                    .header("X-RapidAPI-Key", "rapidApi key")
                    .header("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        getImdbData();
    }
}
