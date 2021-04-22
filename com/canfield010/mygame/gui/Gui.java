package com.canfield010.mygame.gui;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

    public static int screenWidth = 856;
    public static int screenHeight = 482;

    JButton myBtn;
    JButton myBtn2;
    JButton myBtn3;
    JTextArea myTxtArea = new JTextArea("These are my starter words");
    JLabel label;

    FlowLayout flowLayout;
    GridLayout gridLayout;
    BorderLayout borderLayout;
    //BoxLayout boxLayout;


    private Gui(String name) {
        super(name);
        initComponents();
        //addListeners();
    }

    private void initComponents() {
        flowLayout = new FlowLayout();
        gridLayout = new GridLayout(1,1);
        borderLayout = new BorderLayout();
        //boxLayout = new BoxLayout();
        myTxtArea.setEditable(true);
        myBtn = new JButton("Play");
        //myBtn.setLayout(null);
        //myBtn.setSize(screenWidth/10, screenHeight/10);
        //myBtn.setSize(new Dimension(screenWidth/10, screenHeight/10));
        myBtn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //myBtn.setAlignmentY(0.6F);
        myBtn.setVerticalTextPosition(SwingConstants.CENTER);
        //myBtn.setVerticalAlignment(SwingConstants.CENTER);
        //myBtn.setBorder(BorderFactory.createEmptyBorder());
        myBtn2 = new JButton("Second Button!");
        myBtn3 = new JButton("Third button this time!");
        label = new JLabel("testing", SwingConstants.CENTER);
        //label.setVerticalTextPosition(12);
        //myBtn.setBackground(Color.GREEN);
        myBtn2.setBackground(Color.GREEN);
        myBtn3.setBackground(Color.GREEN);
        addListeners();
    }

    public void addListeners() {
        myBtn.addActionListener(e -> {

        });
        /*myBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).setBackground(Color.BLUE);
            }
        });*/
        myBtn2.addActionListener(e -> {

        });

        myBtn3.addActionListener(e -> myBtn3.setBackground(Color.ORANGE));
    }

    private void addComponentsToPane(Container pane) {
        JButton btn = new JButton("Push");


        btn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setAlignmentY(0.6F);
        btn.setVerticalTextPosition(JLabel.CENTER);
        //btn.setVerticalAlignment(SwingConstants.CENTER);
        //btn.setHorizontalAlignment(SwingConstants.CENTER);
        //btn.setHorizontalTextPosition(SwingConstants.CENTER);


        JPanel panel = new JPanel();
        pane.setLayout(gridLayout);
        //pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.setPreferredSize(new Dimension(640, 480));
        //pane.add(myBtn);
        //pane.add(myBtn2);
        //pane.add(myBtn3);
        //myBtn.setSize(new Dimension(100, 100));
        //panel.add(myBtn);
        panel.add(btn);
        pane.add(panel);
        //pane.add(myBtn2, BorderLayout.CENTER);
        //pane.add(myBtn3, BorderLayout.SOUTH);
        //pane.add(myTxtArea, BorderLayout.EAST);
        //pane.add(label, BorderLayout.WEST);

    }
    public static void createAndShowGui(String name) {
        Gui frame = new Gui(name);

        //frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
        frame.setSize(screenWidth, screenHeight);

        frame.initComponents();
        frame.addComponentsToPane(frame.getContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
