package com.example.demo.Controller;
import com.example.demo.WebRequests.*;
import com.example.demo.WebRequests.Rest;
import java.lang.Exception;
import java.util.Scanner;
import java.lang.reflect.Array;
import java.util.ArrayList;
//import org.json;
public class Application {
    static Rest Data;
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        try {


            System.out.println("Enter the choice: 1.)Most Popular Movies");
            int choice = Application.scan.nextInt();
            if (choice == 1) {
                Data = new MostPopular();
                Data.getImdbData();
                System.out.println();
                System.out.println();
//            Data.getClass();
            }
            else{
                System.out.println("invalid options");
            }
//            Rest r1= new GetDetails();
//            r1.getImdbData();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
