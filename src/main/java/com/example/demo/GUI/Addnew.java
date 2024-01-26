package com.example.demo.GUI;

import com.example.demo.GUI.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Addnew extends imdbtest implements ActionListener {
    JFrame f;
    JButton addmovie;
    JTextArea T1, D1, Y1;
    JLabel titlelabel, durationlabel, yearlabel;

    Addnew() throws Exception {
//        super();
        f = new JFrame();
        JLabel heading = new JLabel("<html><h1>ADD NEW MOVIE<h1></html>");
        heading.setBounds(370, 20, 250, 150);
        heading.setSize(220, 30);
        f.add(heading);
        heading.setForeground(Color.BLACK);
        f.setSize(900, 600);
        f.getContentPane().setBackground(Color.WHITE);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titlelabel = new JLabel("<html><h2>Title<h2></html>");
        titlelabel.setBounds(180, 150, 200, 100);
        titlelabel.setSize(210, 30);
        f.add(titlelabel);


        T1 = new JTextArea();
        T1.setBounds(380, 150, 200, 30);
        T1.setBackground(Color.lightGray);
        T1.setForeground(Color.WHITE);
        f.add(T1);

        durationlabel = new JLabel("<html><h2>Duration<h2></html>");
        durationlabel.setBounds(180, 220, 200, 100);
        durationlabel.setSize(210, 30);
        f.add(durationlabel);


        D1 = new JTextArea();
        D1.setBounds(380, 220, 200, 30);
        D1.setBackground(Color.lightGray);
        D1.setForeground(Color.WHITE);
        f.add(D1);

        yearlabel = new JLabel("<html><h2>Year<h2></html>");
        yearlabel.setBounds(180, 290, 200, 100);
        yearlabel.setSize(210, 30);
        f.add(yearlabel);

        Y1 = new JTextArea();
        Y1.setBounds(380, 290, 200, 30);
        Y1.setBackground(Color.lightGray);
        Y1.setForeground(Color.WHITE);
        f.add(Y1);

        addmovie = new JButton("ADD");
        addmovie.setBounds(370, 400, 50, 50);
        addmovie.setSize(100, 30);
        addmovie.setForeground(Color.BLACK);
        addmovie.setBackground(Color.WHITE);
        addmovie.addActionListener(this);
        f.add(addmovie);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addmovie) {
            try {

                // Specify the path of the file to be created
                Path filePath = Paths.get("C:\\Users\\uttka\\Desktop\\MiniProjectJava\\src\\main\\java\\com\\example\\demo\\GUI\\moviefiles\\" + T1.getText() + ".txt");

                // Create the file
                Files.createFile(filePath);
                System.out.println("File created successfully.");

                String content = String.format("Title : %s \n\nMovie duration : %s \n\nYear : %s", T1.getText(), D1.getText(), Y1.getText());
                Files.writeString(filePath, content, StandardOpenOption.WRITE);

                f.dispose();

            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Error creating file: " + ex.getMessage());
            }
        }
    }

//    public static void main(String[] args) throws Exception {
//        try {
//            new Addnew();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
