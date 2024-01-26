package com.example.demo.WebRequests;
import java.net.URI;
import java.net.http.*;
import java.util.Map;

import com.example.demo.Database.Entries;
import org.json.*;

public class GetDetails implements Rest{
    Entries e1;
    @Override
    public void getImdbData() throws Exception{
//        GetDetailer("tt0944947");
    }
    public String GetDetailer(String id) throws Exception {
        try {
            Attributes results = new Attributes();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://imdb8.p.rapidapi.com/title/get-details?tconst="+id))
                    .header("X-RapidAPI-Key", "rapidApi key")
                    .header("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String stre = response.body();
            System.out.println(stre);
            return stre;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public String sendDBMS(String data){
        e1 = new Entries();
        JSONObject jObject = new JSONObject(data);
        Attributes resulter = new Attributes();
        String temp= jObject.getString("id");
        temp = temp.replace("/", "");
        temp = temp.replace(",", "");
        temp = temp.replace(",", "");
        temp = temp.replace("title", "");
        temp = temp.replace("[", "");
        resulter.id = temp;

        resulter.title = jObject.getString("title");
        resulter.duration = jObject.getInt("runningTimeInMinutes");
        resulter.year = jObject.getInt("year");
        JSONObject Jarray  = jObject.getJSONObject("image");

        resulter.urlImg = Jarray.getString("url");
        System.out.println(resulter.id);
        e1.putData(resulter);
        return temp;
    }
}