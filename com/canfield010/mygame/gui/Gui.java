package com.canfield010.mygame.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

    public static int screenWidth = 856;
    public static int screenHeight = 482;
    //public static int screenWidth = 200;
    //public static int screenHeight = 1000;
    public static final boolean DEBUG = false;

    JButton myBtn;
    JButton myBtn2;
    JButton myBtn3;
    JTextArea myTxtArea = new JTextArea("These are my starter words");
    JLabel label;

    JButton playButton = new JButton("Play");
    JButton settingsButton = new JButton("Settings");

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
        //flowLayout.
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



        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setPreferredSize(new Dimension(screenWidth/4, screenHeight/10));
        playButton.setFont(new Font("Arial", Font.PLAIN, Math.min((screenWidth/4)/6, (screenHeight/10))));
        if (!DEBUG) {
            playButton.setContentAreaFilled(false);
        }
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setPreferredSize(new Dimension(screenWidth/4, screenHeight/10));
        settingsButton.setFont(new Font("Arial", Font.PLAIN, Math.min((screenWidth/4)/6, (screenHeight/10))));
        if (!DEBUG) {
            settingsButton.setContentAreaFilled(false);
        }
        settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));




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


        //btn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setAlignmentY(0.6F);
        //btn.setVerticalTextPosition(JLabel.CENTER);
        //btn.setVerticalAlignment(JButton.CENTER);
        //btn.setHorizontalAlignment(SwingConstants.CENTER);
        //btn.setHorizontalTextPosition(SwingConstants.CENTER);

        //btn.setBounds(screenWidth/2, screenHeight/2, screenWidth/5, screenHeight/5);
        btn.setVerticalAlignment(JButton.CENTER);
        btn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setBorderPainted(false);
        //btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        //btn.setSize(new Dimension(screenWidth/10, screenHeight/10));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        pane.setLayout(new GridBagLayout());
        //pane.setLayout(flowLayout);

        //pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.setPreferredSize(new Dimension(screenWidth, screenHeight));
        //btn.setSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //pane.add(myBtn);
        //pane.add(myBtn2);
        //pane.add(myBtn3);
        //myBtn.setSize(new Dimension(100, 100));
        //panel.add(myBtn);
        //panel.add(btn, JPane.CENTER_ALIGNMENT);

        panel.add(playButton);//, Component.CENTER_ALIGNMENT);
        panel.add(settingsButton);//, Component.CENTER_ALIGNMENT);
        //pane.add(playButton);//, SwingConstants.CENTER);
        //pane.add(settingsButton, 1);
        pane.add(panel);


        //btn.setSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setVerticalAlignment(SwingConstants.CENTER);
        //btn.setVerticalTextPosition(JLabel.CENTER);


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
