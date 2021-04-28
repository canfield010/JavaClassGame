package com.canfield010.mygame.gui;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.lowermapsquare.LowerMapSquare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gui extends JFrame {

    public static final int STARTING_SCREEN_WIDTH = 856;
    public static final int STARTING_SCREEN_HEIGHT = 482;
    private int cols = 32;
    private int rows = 32;
    public static final boolean DEBUG = false;
    private JLayeredPane layeredPane = new JLayeredPane();
    JButton[] myBtns = new JButton[1089];
    JPanel btnPanel = new JPanel();

    JButton playButton = new JButton("Play");
    JButton settingsButton = new JButton("Settings");
    JPanel panel = new JPanel();
    JPanel buttonPanel = new JPanel();


    private Gui(String name) {
        super(name);
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(STARTING_SCREEN_WIDTH, STARTING_SCREEN_HEIGHT));
        this.setLayout(new BorderLayout());
        btnPanel.setLayout(null);
        for (int i = 0; i<myBtns.length; i++) {
            myBtns[i] = makeButton(i);
            btnPanel.add(myBtns[i]);
        }




        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setFont(new Font("Arial", Font.PLAIN, Math.min((STARTING_SCREEN_WIDTH /4)/6, (STARTING_SCREEN_HEIGHT /10))));
        if (!DEBUG) {
            playButton.setContentAreaFilled(false);
        }
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setFont(new Font("Arial", Font.PLAIN, Math.min((STARTING_SCREEN_WIDTH /4)/6, (STARTING_SCREEN_HEIGHT /10))));
        if (!DEBUG) {
            settingsButton.setContentAreaFilled(false);
        }
        settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // OMG it took me SO LONG to find this!!! Here's the solution:
        buttonPanel.setOpaque(false);
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());

        resetSizes();
    }

    public void addListeners() {
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resetSizes();
                //playButton.setLocation(buttonPanel.getWidth()/2, buttonPanel.getHeight()/2);
                //settingsButton.setLocation(buttonPanel.getWidth()/2, buttonPanel.getHeight()/2);
            }
        });this.addWindowStateListener(event -> {
            resetSizes();
            //playButton.setLocation(buttonPanel.getWidth()/2, buttonPanel.getHeight()/2);
            //settingsButton.setLocation(buttonPanel.getWidth()/2, buttonPanel.getHeight()/2);
        });

        playButton.addActionListener(e -> {
            System.out.println("Click 1");
        });
        settingsButton.addActionListener(e -> {
            System.out.println("Click 2");
        });
    }

    private void addComponentsToPane() {
        buttonPanel.add(playButton);
        buttonPanel.add(settingsButton);
        panel.add(buttonPanel);
        layeredPane.add(btnPanel, 0, 1);
        layeredPane.add(panel, 0, 0);
        this.add(layeredPane, BorderLayout.CENTER);
    }

    private JButton makeButton(int i){
        return new JButton();
    }
    private void resetSizes() {
        btnPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        panel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        double rowPerColumn = (double)layeredPane.getWidth()/(double)layeredPane.getHeight();
        double columnsPerRow = (double)layeredPane.getHeight()/(double)layeredPane.getWidth();
        int cols1 = (int)Math.floor(Math.sqrt(1089/rowPerColumn));
        int rows1 = (int)Math.floor(1089D/(double)cols1);

        int rows2 = (int)Math.floor(Math.sqrt(1089/columnsPerRow));
        int cols2 = (int)Math.floor(1089D/(double)rows2);
        // choosing minimum values to keep more zoomed in.
        cols = Math.min(cols1, cols2);
        rows = Math.min(rows1, rows2);
        double rowSize;
        double colSize;
        if (layeredPane.getWidth()==0 || layeredPane.getHeight()==0) {
            rowSize = 1;
            colSize = 1;
        } else {
            rowSize = layeredPane.getWidth()/(rows-1);
            colSize = layeredPane.getHeight()/(cols-1);
        }
        for (int index = 0; index<myBtns.length; index++) {
            myBtns[index].setBounds((int)(Math.floor(((double)index)%((double)rows))*colSize), (int)(Math.floor(((double)index)/((double)rows))*rowSize), (int)rowSize+1, (int)colSize+1);
            if (rows==0) {
                rows = 32;
            }
            myBtns[index].setIcon(Main.mapSquares.get(index/rows, index%rows).getImage((int)rowSize, (int)rowSize));
        }

        layeredPane.moveToFront(panel);
    }


    public static void createAndShowGui(String name) {
        Gui frame = new Gui(name);



        frame.initComponents();
        MapSquare.initalizeImages();
        frame.addListeners();
        frame.addComponentsToPane();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
