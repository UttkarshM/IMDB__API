package com.example.demo.GUI;

import com.example.demo.Database.Entries;
import com.example.demo.WebRequests.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.json.*;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.json.simple.parser.JSONParser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Iterator;

public class imdbtest implements ActionListener{
    JButton b;
    Rest MostPopular;
    GetDetails getIt;

    static ArrayList<String> Ids = new ArrayList<String>();
    JComboBox c;
    static GetDetails GetDet;
    Entries entry = new Entries();
    static String uri = "mongodb uri";


    String currentId;
    String result;

    public void details(String vid) throws Exception{
        String text = (String) this.c.getEditor().getItem();
        Attributes a = new Attributes();
        a = entry.getData(text);
        new FileContentDisplay(a.title,a.id,a.duration,a.urlImg,a.year);

    }
    public imdbtest() throws Exception
    {
        Entries e1 = new Entries();
        ArrayList<String> Lister = e1.getNames();
//        System.out.println("now");

        JFrame f =new JFrame();
        JLabel title = new JLabel("<html><h1>IMDB-API<h1></html>");
        title.setBounds(370,20,200,100);
        title.setSize(190,30);
        f.add(title);
        title.setForeground(Color.decode("#F99417"));

        JLabel w = new JLabel("<html><h1>Welcome!<h1></html>");
        w.setBounds(370,130,200,100);
        w.setSize(190,30);
        f.add(w);
        w.setForeground(Color.decode("#F99417"));

        JLabel m = new JLabel("<html><center><h2>Explore and enjoy a vast collection of movies <br> from the comfort of your own screen</h2></center></html>");
        m.setBounds(240,200,200,100);
        m.setForeground(Color.decode("#F99417"));
        m.setSize(600,90);
        f.add(m);



        MostPopular mp = new MostPopular();
        mp.getImdbData();

        System.out.println(Lister);

        String[] movies = new String[Lister.size()+20];
        System.out.println(Lister.size());
        movies[0]="";
        for(int i=1;i<Lister.size();i+=1){

            movies[i] = (Lister.get(i));
        }
        c = new JComboBox(movies);
        c.setBounds(340,350,200,35);
        f.add(c);

        b = new JButton("Search");
        b.setBounds(380,420,200,35);
        b.setBackground(Color.decode("#035397"));
        b.setForeground(Color.decode("#F99417"));
        b.setSize(100,40);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    details("");
                }
                catch(Exception eb){
                    eb.printStackTrace();
                }
            }
        });
        f.add(b);

        f.setSize(900,600);
        f.getContentPane().setBackground(Color.BLACK);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public ArrayList<String> getNames(){
        ArrayList<String> Lister = new ArrayList<String>();
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
            }

            for(String it1:HashSett){
                Lister.add((String)it1);
            }

            System.out.println(Lister);
            return Lister;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b){
            String name = (String) c.getSelectedItem();
            System.out.println(name);
        }
    }
    public static void main(String[] args) throws Exception {
////        imdbtest t = new imdbtest();
//        MostPopular p1 = new MostPopular();
//        p1.getImdbData(Ids);
////        GetDetails g1 = new GetDetails();
////        g1.GetDetailer("tt15314262");
////        details("");
        new imdbtest();
    }
}
