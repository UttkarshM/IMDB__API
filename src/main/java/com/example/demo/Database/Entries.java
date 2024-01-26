package com.example.demo.Database;
import com.example.demo.WebRequests.Attributes;
import com.mongodb.client.*;
import org.bson.Document;
import org.json.*;
//import org.json.simple.*;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.JSONParser;

import java.util.*;

public class Entries {
    public ArrayList<String> temp1 = new ArrayList<String>();
    static String uri = "the mongodb uri";
    public Attributes getData(String id){
        // Replace the placeholder with your MongoDB deployment's connection string
        if(id.isEmpty()){
            id = "";
        }
        Attributes atr = new Attributes();
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sampleDB");
            MongoCollection<Document> collection = database.getCollection("sampleCollection");
//            for(Document doc:collection.find(eq("_id", "i"))){
//                if (doc != null) {
//                    System.out.println(doc.toJson());
//                } else {
//                    System.out.println("No matching documents found.");
//                }
//            }
//            System.out.println(doc.);
            FindIterable<Document> iterDoc = collection.find();
            Iterator it = iterDoc.iterator();
            while (it.hasNext()) {
                Document doc = (Document)it.next();
                String str = doc.toJson().toString();
                JSONObject jObject = new JSONObject(str);
                String ider = jObject.getString("title");
                System.out.println(doc.toJson().toString());
//                System.out.println(ider+" - "+id);
                if(Objects.equals(ider, id)){
                    System.out.println("found");
                    atr.id = jObject.getString("id");
                    atr.title = jObject.getString("title");
                    atr.duration = jObject.getInt("duration");
                    atr.year = jObject.getInt("year");
                    atr.urlImg = jObject.getString("image url");
//                    JSONObject Jarray  = jObject.getJSONObject("image");

//                    resulter.urlImg = Jarray.getString("url");
//                    atr.id = ider;
//                    atr.title = jo.getString("title:");
//                    atr.duration = jo.getInt("duration:");
//                    atr.urlImg = jo.getString("image url:");
//                    atr.year = jo.getInt("year:");
                    break;
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return atr;
    }
     public ArrayList<String> getNames(){
        Set<String> HashSett = new HashSet<String>();
         try (MongoClient mongoClient = MongoClients.create(uri)) {
             MongoDatabase database = mongoClient.getDatabase("sampleDB");
             MongoCollection<Document> collection = database.getCollection("sampleCollection");

             FindIterable<Document> iterDoc = collection.find();
             Iterator it = iterDoc.iterator();
             while (it.hasNext()) {
                 Document doc = (Document)it.next();
                 String str = doc.toJson().toString();
                 JSONObject jObject = new JSONObject(str);
                 String ider = jObject.getString("title");
                 HashSett.add(ider);
//                 System.out.println(ider);
             }

             for(String it1:HashSett){
                 temp1.add((String)it1);
             }

             System.out.println(temp1);
             return temp1;
         }
         catch(Exception e){
             e.printStackTrace();
         }
         return null;
    }

    public void putData(Attributes ab){
        try(MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("sampleDB");
            MongoCollection<Document> collection = database.getCollection("sampleCollection");
            Document document = new Document("id",ab.id);
            document.append("title",ab.title);
            document.append("duration",ab.duration);
            document.append("image url",ab.urlImg);
            document.append("year",ab.year);
            collection.insertOne(document);
        }
    }

//    public  void main(String[] args) {
////        putData();
//        Attributes a = new Attributes();
//        a=getData("");
//        System.out.println(a.title);
//    }

}