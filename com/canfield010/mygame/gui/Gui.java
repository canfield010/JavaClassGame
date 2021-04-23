package com.canfield010.mygame.gui;

import com.canfield010.mygame.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Gui extends JFrame {

    public static int screenWidth = 856;
    public static int screenHeight = 482;
    //public static int screenWidth = 200;
    //public static int screenHeight = 1000;
    public static final boolean DEBUG = false;

    //JButton myBtn;
    //JButton myBtn2;
    //JButton myBtn3;
    //JTextArea myTxtArea = new JTextArea("These are my starter words");
    //JLabel label;

    JButton playButton = new JButton("Play");
    JButton settingsButton = new JButton("Settings");
    JPanel panel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel mapSquareGrid = new JPanel();
    JLayeredPane jLayeredPane = new JLayeredPane();

    JButton[] jButtons = {playButton, settingsButton};

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
        //flowLayout = new FlowLayout();
        //flowLayout.
        //gridLayout = new GridLayout(1,1);
        //borderLayout = new BorderLayout();
        //boxLayout = new BoxLayout();
        //myTxtArea.setEditable(true);
        //myBtn = new JButton("Play");
        //myBtn.setLayout(null);
        //myBtn.setSize(screenWidth/10, screenHeight/10);
        //myBtn.setSize(new Dimension(screenWidth/10, screenHeight/10));
        //myBtn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //myBtn.setAlignmentY(0.6F);
        //myBtn.setVerticalTextPosition(SwingConstants.CENTER);
        //myBtn.setVerticalAlignment(SwingConstants.CENTER);
        //myBtn.setBorder(BorderFactory.createEmptyBorder());
        //myBtn2 = new JButton("Second Button!");
        //myBtn3 = new JButton("Third button this time!");
        //label = new JLabel("testing", SwingConstants.CENTER);
        //label.setVerticalTextPosition(12);
        //myBtn.setBackground(Color.GREEN);
        //myBtn2.setBackground(Color.GREEN);
        //myBtn3.setBackground(Color.GREEN);



        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //playButton.setPreferredSize(new Dimension(screenWidth/4, screenHeight/10));
        playButton.setFont(new Font("Arial", Font.PLAIN, Math.min((screenWidth/4)/6, (screenHeight/10))));
        if (!DEBUG) {
            playButton.setContentAreaFilled(false);
        }
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //settingsButton.setPreferredSize(new Dimension(screenWidth/4, screenHeight/10));
        settingsButton.setFont(new Font("Arial", Font.PLAIN, Math.min((screenWidth/4)/6, (screenHeight/10))));
        if (!DEBUG) {
            settingsButton.setContentAreaFilled(false);
        }
        settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(new GridBagLayout());
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        mapSquareGrid.setLayout(new GridLayout(33, 33));




        //addListeners();
    }

    public void addListeners(JFrame frame) {
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                //screenWidth = componentEvent.
                for (JButton button: jButtons) {
                    button.setFont(new Font("Arial", Font.PLAIN, Math.min((frame.getWidth()/4)/6, (frame.getHeight()/10))));
                }
            }
        });
        playButton.addActionListener(e -> {
            //Main.playerPosition;
            System.out.println("Click 1");
            //frame.set
        });
        settingsButton.addActionListener(e -> {
            System.out.println("Click 2");
        });
        /*myBtn.addActionListener(e -> {

        });
        //myBtn2.addActionListener(new ActionListener() {
            //@Override
            //public void actionPerformed(ActionEvent e) {
                //((JButton)e.getSource()).setBackground(Color.BLUE);
            //}
        //});
        myBtn2.addActionListener(e -> {

        });

        myBtn3.addActionListener(e -> myBtn3.setBackground(Color.ORANGE));*/
    }

    private void addComponentsToPane(Container pane, JFrame frame) {
        //JButton btn = new JButton("Push");


        //btn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setAlignmentY(0.6F);
        //btn.setVerticalTextPosition(JLabel.CENTER);
        //btn.setVerticalAlignment(JButton.CENTER);
        //btn.setHorizontalAlignment(SwingConstants.CENTER);
        //btn.setHorizontalTextPosition(SwingConstants.CENTER);

        //btn.setBounds(screenWidth/2, screenHeight/2, screenWidth/5, screenHeight/5);
        //btn.setVerticalAlignment(JButton.CENTER);
        //btn.setPreferredSize(new Dimension(screenWidth/10, screenHeight/10));
        //btn.setBorderPainted(false);
        //btn.setOpaque(false);
        //btn.setContentAreaFilled(false);
        //btn.setSize(new Dimension(screenWidth/10, screenHeight/10));


        //pane.setLayout(new GridBagLayout());
        frame.setLayout(new BorderLayout());
        pane.setLayout(new BorderLayout());
        //pane.setLayout(new FlowLayout());

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

        buttonPanel.add(playButton);
        buttonPanel.add(settingsButton);
        panel.add(buttonPanel);
        //panel.add(playButton);//, Component.CENTER_ALIGNMENT);
        //panel.add(settingsButton);//, Component.CENTER_ALIGNMENT);
        //pane.add(playButton);//, SwingConstants.CENTER);
        //pane.add(settingsButton, 1);
        //mapSquareGrid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //for (int index = 0; index<200; index++) {
            //mapSquareGrid.add(new JButton("Number 0"), index);
        //}

        //jLayeredPane.setLayout(new BorderLayout());
        //jLayeredPane.setLayer(mapSquareGrid, 0, 0);
        //jLayeredPane.setLayer(panel, 1, 0);
        //jLayeredPane.add(mapSquareGrid);
        //jLayeredPane.add(panel);

        //pane.add(mapSquareGrid);
        pane.add(panel);

        //pane.add(jLayeredPane);
        //frame.setLayeredPane(jLayeredPane);
        //frame.add(jLayeredPane);


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

        frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
        //frame.setSize(screenWidth, screenHeight);

        frame.initComponents();
        frame.addListeners(frame);
        frame.addComponentsToPane(frame.getContentPane(), frame);
        /*frame.setLayout(new BorderLayout());
        frame.add(frame.jLayeredPane, BorderLayout.CENTER);
        frame.buttonPanel.add(frame.playButton);
        frame.buttonPanel.add(frame.settingsButton);
        frame.panel.add(frame.buttonPanel);
        frame.panel.setBackground(Color.BLUE);
        frame.jLayeredPane.add(frame.panel, 0, 0);*/

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
