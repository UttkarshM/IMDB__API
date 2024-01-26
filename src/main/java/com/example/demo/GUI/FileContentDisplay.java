package com.example.demo.GUI;

import com.example.demo.WebRequests.Attributes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class FileContentDisplay extends imdbtest{
    static JFrame frame;
    static JLabel image;
    public String source;
    FileContentDisplay(String title,String id,int duration,String urlImg,int year) throws Exception {
        frame = new JFrame("File Content Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.decode("#363062"));
        textArea.setForeground(Color.decode("#F99417"));
        textArea.setFont(new Font("Serif", Font.BOLD, 25));
        textArea.setBounds(40,40,500,200);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Load file content into the JTextArea
        loadFileContent(textArea,title,id,duration,year);
        frame.add(textArea);


//      Required to display the image
        image = loadImage(urlImg);
        frame.getContentPane().add(image);
/*
        not required for now or for rendering image
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
*/
        frame.getContentPane().setBackground(Color.decode("#363062"));
        frame.setSize(900, 600);
        frame.setVisible(true);
    }

    /* where we actually load the url of image and display it */
    private static JLabel loadImage(String urlSource) throws IOException{
 URL url = new URL(urlSource);
        BufferedImage image = ImageIO.read(url);
        return new JLabel(new ImageIcon(image));
    }


    private static void loadFileContent(JTextArea textArea,String title,String id,int duration,int year) {
//        String filename="Black Panther Wakanda Forever";
        try {//(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\uttka\\Desktop\\MiniProjectJava\\src\\main\\java\\com\\example\\demo\\GUI\\moviefiles\\"+filename+".txt"))) {
            StringBuilder content = new StringBuilder();
            String line="";


            line+=("id:"+id+"\n");

            line+=("title:"+title+"\n");

            line+=("duration:"+duration+"\n");

            line+=("year of release:"+year+"\n");

            textArea.setText(line);
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

