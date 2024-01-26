package com.example.demo.WebRequests;

import com.example.demo.Database.Entries;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class MostPopular implements Rest{

    ArrayList<String> Ids = new ArrayList<String>();
    void ParseIds(String name){
        int z=10;
        String temp = "";
        for(int i=0;i<name.length();i+=1){
            if(this.Ids.size()>=z){
                return;
            }
            if(name.charAt(i) == '\"'){
                if(!temp.isEmpty()) {
                    temp = temp.replace("/", "");
                    temp = temp.replace(",", "");
                    temp = temp.replace(",", "");
                    temp = temp.replace("title", "");
                    temp = temp.replace("[", "");
//                    System.out.print(temp);
                    if (temp.isEmpty()) {
                        continue;
                    }
                    this.Ids.add(temp);
                    temp = "";

                    continue;
                }
            }
            temp+=name.charAt(i);
        }

    }

    void GetDetails(String id) throws Exception {
        try {
//            System.out.println(this.Ids.get(1));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://imdb8.p.rapidapi.com/title/get-details?tconst=" + id))
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
    public  void details(String vid) throws Exception{
        GetDetails GetDet;
        Attributes resulter = new Attributes();
        GetDet = new GetDetails();
        String data = GetDet.GetDetailer(vid);
//        if(!this.Ids.contains(vid)) {
            String id = GetDet.sendDBMS(data);
//        }
    }
    public void getImdbData(ArrayList<String> IDS) throws Exception{

        try{
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://imdb8.p.rapidapi.com/title/get-most-popular-movies?homeCountry=US&currentCountry=US&purchaseCountry=US"))
                    .header("X-RapidAPI-Key", "rapidApi key")
                    .header("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String temp = response.body();

            ParseIds(temp);

            for (String it:
                    IDS) {
                this.Ids.add(it);
            };
            for(int i=0;i<this.Ids.size();i++){
                details(this.Ids.get(i));

            }
            IDS = this.Ids;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<String> GetList(){
        return this.Ids;
    }

    public void main(String[] args) throws Exception {

        getImdbData();
    }

    @Override
    public void getImdbData() throws Exception {

    }
}
